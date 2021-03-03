package com.proje.Service;

import java.util.List;

import com.proje.model.Category;

public interface ICategoryService {
	Category findCategoryById(int id);
	
	List<Category> findCategories();
	
}
