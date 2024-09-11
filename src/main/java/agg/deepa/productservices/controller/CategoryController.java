package agg.deepa.productservices.controller;

import agg.deepa.productservices.services.CategoryServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private CategoryServices categoryServices;
    public CategoryController(CategoryServices categoryServices){
        this.categoryServices = categoryServices;
    }
    @GetMapping("/category")
    public String getAllCategories(){
        return "Getting all categories";
    }
    public String getProductInCategories(){
        return "Get products in category";
    }

}
