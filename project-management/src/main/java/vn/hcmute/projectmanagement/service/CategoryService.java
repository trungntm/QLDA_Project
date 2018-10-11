package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Category;

import java.util.List;

public interface CategoryService {
    Category retrieveById(long id);
    List<Category> retrieveAllCategories();
    Category InsertCategory(Category category);
    void DeleteCategory(long id);
}
