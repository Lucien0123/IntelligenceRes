package tcu.lucien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tcu.lucien.dao.ProductDao;
import tcu.lucien.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/25.
 */
@Controller
@RequestMapping("/shopcart")
public class ShopcartController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/shopcartlist")
    public String shopcartList(){

        return "shoppingcart0312";
    }

    @RequestMapping("/addshopcart")
    @ResponseBody
    public String addShopCart(Model model, @RequestParam(value = "productid") String productId,
                              @RequestParam(value = "pnum") String pnum, HttpServletRequest request){

        Integer num = Integer.parseInt(pnum);
        Integer pid = Integer.parseInt(productId);
        Product product = productDao.findById(pid);    //获取购买的商品对象

        HttpSession session = request.getSession();
        Map<Product, Integer> cartMap = (Map<Product, Integer>) session.getAttribute("cart");
        if (cartMap == null){
            cartMap = new HashMap<Product, Integer>();
            session.setAttribute("cart", cartMap);
        }

        Integer oldNum = cartMap.get(product);
        if (oldNum != null){
            cartMap.put(product, Integer.valueOf(num.intValue() + oldNum.intValue()));
        } else {
            cartMap.put(product, num);
        }
        return "ok";
    }

    @RequestMapping("deleteprod")
    public String deleteprodFormCart(HttpServletRequest request,
                                     @RequestParam(value = "id") Integer id){

        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
        if(cart != null){

            Product temp = null;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                if(entry.getKey().getId() == id){
                    temp = entry.getKey();
                    break;
                }
            }

            if(temp != null){
                cart.remove(temp);
            }
        }
        return "shoppingcart0312";
    }

}
