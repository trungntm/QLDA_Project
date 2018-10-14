package vn.hcmute.projectmanagement.controller;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.api.v1.dto.ProductDto;
import vn.hcmute.projectmanagement.entity.Category;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.service.CategoryService;
import vn.hcmute.projectmanagement.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/{id}")
    public DataReturnList<ProductDto> Product(@PathVariable("id") long id){
        List<ProductDto> productDtos = productService.getAllProductByCategory(id);

        DataReturnList<ProductDto> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(productDtos);
        dataReturnList.setMessage("Get list product success");
        return dataReturnList;
    }

    @PostMapping("/create/{categoryid}")
    public DataReturnOne<Product> Create(@RequestBody Product product, @PathVariable  long categoryid){
        Category category = categoryService.retrieveById(categoryid);

        Product product1 = productService.InsertProduct(product);
        product1.getCategories().add(category);
        DataReturnOne<Product> dataReturnOne = new DataReturnOne<>();
        if(product1!=null){
            dataReturnOne.setData(product1);
            dataReturnOne.setMessage("Create product success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Create product fail");
        }
        return dataReturnOne;
    }

    @PutMapping("/update")
    public DataReturnOne<Product> UpdateProduct(@RequestBody Product product){
        Product product1 = productService.InsertProduct(product);
        DataReturnOne<Product> dataReturnOne = new DataReturnOne<>();
        if(product1!=null){
            dataReturnOne.setData(product1);
            dataReturnOne.setMessage("Update product success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update product fail");
        }
        return dataReturnOne;
    }

    @DeleteMapping("/delete")
    public DataReturnOne<Product> DeleteProduct(@RequestBody Product product){
        DataReturnOne<Product> dataReturnOne = new DataReturnOne<>();
        try {
            productService.DeleteProduct(product.getId());
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete product success");
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete product fail" + e.getMessage());
        }
        return dataReturnOne;
    }
}
