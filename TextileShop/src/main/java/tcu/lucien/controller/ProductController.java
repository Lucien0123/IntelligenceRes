package tcu.lucien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tcu.lucien.dao.ProductDao;
import tcu.lucien.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping("productdetail")
    public String productdetail(@RequestParam(value = "productid") Integer productid,
                                Model model){
        Product product = new Product();
        product = productDao.findById(productid);
        model.addAttribute("product", product);
        System.out.println(product.getDetaildescription());
        return "productdetail0312";
    }

    @RequestMapping("productlist")
    public String productlist(@RequestParam(value = "cateid") String cateId,
                              Model model){
        List<Product> productList = new ArrayList<Product>();
        productList = productDao.findByCateid(Integer.parseInt(cateId));
        model.addAttribute("productList", productList);
        return "productlist0312";
    }
}
