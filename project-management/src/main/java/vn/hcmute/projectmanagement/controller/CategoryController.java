package vn.hcmute.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnList;
import vn.hcmute.projectmanagement.api.v1.data.DataReturnOne;
import vn.hcmute.projectmanagement.entity.Category;
import vn.hcmute.projectmanagement.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public DataReturnList<Category> Category(){
        List<Category> categories = categoryService.retrieveAllCategories();
        DataReturnList<Category> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(categories);
        dataReturnList.setMessage("Get list categry success");
        return dataReturnList;
    }
    @PostMapping("/create")
    public DataReturnOne<Category> CreateCategory(@RequestBody Category category){
        DataReturnOne<Category> dataReturnOne = new DataReturnOne<>();
        Category category1 = categoryService.InsertCategory( category);
        if(category1 != null){
            dataReturnOne.setMessage("Insert Category Success");
            dataReturnOne.setData(category1);
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Insert Category Fail");
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PutMapping("/update")
    public DataReturnOne<Category> UpdateCategory(@RequestBody Category category){
        DataReturnOne<Category> dataReturnOne = new DataReturnOne<>();
        Category category1 = categoryService.InsertCategory( category);
        if(category1 != null){
            dataReturnOne.setMessage("Update Category Success");
            dataReturnOne.setData(category1);
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Update Category Fail");
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }
    @DeleteMapping("/delete")
    public  DataReturnOne<Category> DeleteCategory(@RequestBody Category category){
        DataReturnOne<Category> dataReturnOne = new DataReturnOne<>();
        try{
            categoryService.DeleteCategory(category.getId());
            dataReturnOne.setMessage("Delete Category Success");
            dataReturnOne.setData(null);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }
}
