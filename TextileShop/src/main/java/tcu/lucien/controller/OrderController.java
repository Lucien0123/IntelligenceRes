package tcu.lucien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tcu.lucien.dao.AddressDao;
import tcu.lucien.dao.OrderDao;
import tcu.lucien.dao.ProductDao;
import tcu.lucien.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/25.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/orderlist")
    public String orderList(@RequestParam(value = "id") String[] idStrs,
                            @RequestParam(value = "amount") String [] amountStrs,
                            HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        int allAmount = 0;
        BigDecimal allPrice = new BigDecimal(0.00);
        BigDecimal allPaymentPrice = new BigDecimal(0.00);
        List<Item> items = new ArrayList<Item>();

        int length = idStrs == null ? 0 : idStrs.length;
        for(int i = 0; i < length; i++){
            Integer id = Integer.valueOf(idStrs[i]);
            int amount = Integer.parseInt(amountStrs[i]);
            allAmount += amount; //计算购物的总数量

            //效率有点低
            Product product = productDao.findOne(id);
            //要把选购的商品转换成订单项对象
            Item item = new Item();
            item.setAmount(amount);
            item.setProduct_id(id);
            item.setProduct(product);

            BigDecimal am = new BigDecimal(amount);
            BigDecimal total_price = product.getPrice().multiply(am);
            item.setTotal_price(total_price);

            BigDecimal payment_price = product.getSaleprice().multiply(am);
            item.setPayment_price(payment_price);
            items.add(item);

            allPrice = allPrice.add(total_price); //计算总金额
            allPaymentPrice = allPaymentPrice.add(payment_price); //计算要支付的总金额

            //改购物车中的相应商品的数量
            @SuppressWarnings("unchecked")
            Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
            cart.put(product, Integer.valueOf(amount));
        }

        //订单实体类
        Orders order = new Orders();
        order.setItems(items); //订单的每个订单项
        order.setTotal_amount(allAmount); //订单的总物品数量
        order.setTotal_price(allPrice);//订单的总金额
        order.setPayment_price(allPaymentPrice); //订单的实际支付金额
        order.setCreate_time(new Date());

        //把当前订单数据存储到session中
        session.setAttribute("curr_order", order);

        //判断用户有没有登录
        User user = (User)session.getAttribute("current_user");
        if(user == null){ //没有登录，就跳转到登录页面

            model.addAttribute("msg", "提交订单前,请先登录!");
            return "userlogin0312";

        }else{//登录后的，跳转结算页面
            List<Address> addressList = addressDao.findByUsername(user.getUsername());
            model.addAttribute("addressList", addressList);
            return "orderlist0312";
        }
    }

    @RequestMapping("paymentOrder")
    public String paymentOrder(@RequestParam(value = "addressid") Integer addressid,
                               @RequestParam(value = "remark") String remark,
                               HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Orders order = (Orders)session.getAttribute("curr_order");
        User user = (User)session.getAttribute("current_user");
        order.setBuyerId(user.getId());
        order.setRemark(remark);
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        order.setNumber(df.format(new Date())); //生成一个有意义订单编号
        order.setStatus(2);

        Address address = addressDao.findById(addressid);
        order.setContact(address.getContact());
        order.setMobile(address.getMobile());
        order.setStreet(address.getStreet());
        order.setZipcode(address.getZipcode());

        //清除购物车中的数据
        session.removeAttribute("cart");

        orderDao.save(order);
        return "redirect:/user/userindexpage";
    }
}
