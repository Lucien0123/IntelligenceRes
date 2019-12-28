package tcu.lucien.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tcu.lucien.dao.AddressDao;
import tcu.lucien.dao.OrderDao;
import tcu.lucien.dao.UserDao;
import tcu.lucien.entity.Address;
import tcu.lucien.entity.Orders;
import tcu.lucien.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/userlogin0312")
    public String userlogin(){

        return "userlogin0312";
    }

    @RequestMapping("/userregister")
    public String userregister(){
        return "userregister";
    }

    @RequestMapping("/userindexpage")
    public String userIndex(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User current_user = new User();
        current_user = (User) session.getAttribute("current_user");
        Integer userid = current_user.getId();
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList = orderDao.findByBuyerId(userid);
        model.addAttribute("orders", ordersList);

        return "user/userindex";
    }

    @RequestMapping("/updatepsw")
    public String updatePsw(){

        return "user/indexupdatepsw";
    }

    @RequestMapping("/profileinfor")
    public String profileInfor(){

        return "user/indexprofile";
    }

    @RequestMapping("address")
    public String address(Model model, HttpServletRequest request){
        List<Address> addressList = new ArrayList<Address>();
        HttpSession session = request.getSession();
        User current_user = new User();
        current_user = (User) session.getAttribute("current_user");
        addressList = addressDao.findByUsername(current_user.getUsername());
        model.addAttribute("addresslist", addressList);
        return "user/indexaddress";
    }

    //---------------------------------

    @RequestMapping("/login")
    public String login(Model model, User user, HttpServletRequest request){
        System.out.println("用户名：" + user.getUsername());
        User cur_user = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (cur_user != null){
            HttpSession session = request.getSession();
            session.setAttribute("current_user", cur_user);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "用户名密码错误，请重新输入");
            return "userlogin0312";
        }
    }

    @RequestMapping("/loginout")
    public String loginOut(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "userlogin0312";
    }

    @RequestMapping("/register")
    public String register(Model model, User user, @RequestParam(value="confirmpsw") String confirpsw){
        System.out.println("注册用户名：" + user.getUsername());
        if("".equals(user.getUsername()) || "".equals(user.getPassword())){
            model.addAttribute("msg", "请输入用户名和密码");
            return "userregister";
        } else if (user.getPassword().equals(confirpsw)){
            userDao.save(user);
            return "userlogin0312";
        } else {
            model.addAttribute("msg", "两次密码输入不一致");
            return "userregister";
        }
    }

    @RequestMapping("/modProfileInfor")
    @ResponseBody
    public String modProfileInfor(Model model, HttpServletRequest request, User user){

        HttpSession session = request.getSession();
        System.out.println(user.getEmail() + user.getNickname() + user.getRealname() + user.isGender());
        User current_user = (User) session.getAttribute("current_user");
        current_user.setNickname(user.getNickname());
        current_user.setRealname(user.getRealname());
        current_user.setEmail(user.getEmail());
        current_user.setGender(user.isGender());
        userDao.saveAndFlush(current_user);
        session.setAttribute("current_user", current_user);
        return "ok";
    }

    @RequestMapping("/modpsw")
    public String modPsw(Model model, HttpServletRequest request,
                         @RequestParam(value = "oldpsw") String oldpsw,
                         @RequestParam(value = "newpsw") String newpsw){

        HttpSession session = request.getSession();
        User current_user = (User) session.getAttribute("current_user");
        if (current_user.getPassword().equals(oldpsw) && !"".equals(newpsw)){
            current_user.setPassword(newpsw);
            userDao.saveAndFlush(current_user);
            model.addAttribute("msg", "修改密码成功");
            return "userlogin0312";
        } else {
            model.addAttribute("msg", "请确认两次密码输入正确");
            return "redirect:/user/updatepsw";
        }
    }

    @RequestMapping("/defaultaddress")
    public String defaultAddress(@RequestParam(value = "addressid") String addresId, Model model,
                                 HttpServletRequest request){
        System.out.println("addressId" + addresId);
        HttpSession session = request.getSession();
        User current_user = (User) session.getAttribute("current_user");
        addressDao.defaultAddress(current_user.getUsername());
        addressDao.defaultAddress(current_user.getUsername(), Integer.parseInt(addresId));

        return "redirect:/user/address";
    }

    @RequestMapping("/addAddress")
    public String addAddress(Model model, HttpServletRequest request, Address address){
        HttpSession session = request.getSession();
        User current_user = (User) session.getAttribute("current_user");
        address.setUsername(current_user.getUsername());
        address.setDefaultvalue(false);
        addressDao.save(address);

        return "redirect:/user/address";
    }

    @RequestMapping("updateaddress")
    public String updateaddress(@RequestParam(value = "addressid") Integer addressid, Address address){

        Address oldaddress = new Address();
        oldaddress = addressDao.findById(addressid);
        oldaddress.setContact(address.getContact());
        oldaddress.setMobile(address.getMobile());
        oldaddress.setStreet(address.getStreet());
        oldaddress.setZipcode(address.getZipcode());
        oldaddress.setDefaultvalue(address.isDefaultvalue());
        addressDao.saveAndFlush(oldaddress);

        return "redirect:/user/address";
    }

    @RequestMapping("deleteaddress")
    public String deleteaddress(@RequestParam(value = "addressid") Integer addressid){
        addressDao.delete(addressid);
        return "redirect:/user/address";
    }
}
