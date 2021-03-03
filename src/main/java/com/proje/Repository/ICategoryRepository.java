package com.proje.Repository;

import java.util.List;

import com.proje.model.Category;

public interface ICategoryRepository {
	Category findCategoryById(int id);
	
	List<Category> findCategories();
	
}
