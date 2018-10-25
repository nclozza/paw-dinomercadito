package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public ModelAndView index(@RequestParam(value = "filter", required = false) final String filter) {
        ModelAndView mav = new ModelAndView("products");
        if (filter == null || filter.isEmpty()) {
            List<Product> productList = productService.findAllProducts();
            List<String> brandsList = productService.findAllAttributeValuesForFilter("brand");
            List<String> operativeSystemsList = productService.findAllAttributeValuesForFilter("operativeSystem");
            List<String> ramList = productService.findAllAttributeValuesForFilter("ram");
            List<String> storageList = productService.findAllAttributeValuesForFilter("storage");
            List<String> attributesList = productService.getAllAttributesForFiltering();

            mav.addObject("products", productList);
            mav.addObject("brands", brandsList);
            mav.addObject("operativeSystems", operativeSystemsList);
            mav.addObject("ramSizes", ramList);
            mav.addObject("storageSizes", storageList);
            mav.addObject("attributesToFilter", attributesList);

        } else {
            List<Product> productList = productService.findProductsByFilter(filter);
            if(productList.isEmpty())
                mav.addObject("zeroProducts", true);
            else
                mav.addObject("products", productList);
        }

        mav.addObject("filter", filter);
        //for (String attribute : attributes)
        //    mav.addObject(attribute + "s", productService.findAllAttributeValuesForFilter(attribute));

        return mav;
    }
}
