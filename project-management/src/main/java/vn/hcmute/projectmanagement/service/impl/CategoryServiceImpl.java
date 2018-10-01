package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Category;
import vn.hcmute.projectmanagement.exception.Error404;
import vn.hcmute.projectmanagement.repository.CategoryRepository;
import vn.hcmute.projectmanagement.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category retrieveById(long id) {
        Optional<Category> category = categoryRepository.findCategoriesById(id);
        if(!category.isPresent())
            throw new Error404("Category Not Found");
        return category.get();
    }

    @Override
    public List<Category> retrieveAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category InsertCategory(Category category){
        return categoryRepository.save(category);
    }
    @Override
    public boolean DeleteCategory(long id){
        Category category = categoryRepository.findCategoriesById(id).get();
        if(category != null){
            try {
                category.setStatus("false");
                categoryRepository.save(category);
                return true;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }
}
