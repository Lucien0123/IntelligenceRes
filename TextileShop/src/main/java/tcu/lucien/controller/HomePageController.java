package tcu.lucien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tcu.lucien.dao.*;
import tcu.lucien.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
@Controller
@Configuration
@ComponentScan("tcu.lucien.dao")
public class HomePageController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/")
    public String homepage(Model model, HttpServletRequest request){

        /* 获取分类链接 */
        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryDao.findAll();
        model.addAttribute("categorys", categoryList);
        HttpSession session = request.getSession();
        session.setAttribute("categorys", categoryList);

        /* 获取大图标链接 */
        List<Product> productList = new ArrayList<Product>();
        productList = productDao.findByType(1);
        model.addAttribute("bigProducts", productList);

        /* 获取中图标链接 */
        List<Product> midproductList = new ArrayList<Product>();
        midproductList = productDao.findByType(2);
        for(int j=0;j<midproductList.size()/2;j++){
            List<Product> list = new ArrayList<Product>();
            list.add(midproductList.get(2*j));
            list.add(midproductList.get(2*j+1));
            model.addAttribute("midProducts"+j, list);
        }

        /* 获取小图标链接 */
        List<Product> smallproductList = new ArrayList<Product>();
        smallproductList = productDao.findByType(3);
        for(int y=0;y<smallproductList.size()/4;y++){
            List<Product> list = new ArrayList<Product>();
            list.add(smallproductList.get(4*y));
            list.add(smallproductList.get(4*y+1));
            list.add(smallproductList.get(4*y+2));
            list.add(smallproductList.get(4*y+3));
            model.addAttribute("smallProducts"+y, list);
        }

        return "homepage";
    }

    @RequestMapping("/aboutus")
    public String aboutUs(){

        return "aboutus0312";
    }

    //----------------------------------------

}
