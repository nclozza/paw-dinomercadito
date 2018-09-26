package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("products");
        List<Product> productList = productService.findAllProducts();
        mav.addObject("products", productList);

        return mav;
    }
}
