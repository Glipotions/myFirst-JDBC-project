package com.proje.Service.Implements;

import java.util.List;

import com.proje.Repository.ICategoryRepository;
import com.proje.Repository.Implements.CategoryRepository;
import com.proje.Service.ICategoryService;
import com.proje.model.Category;

public class CategoryService implements ICategoryService{
	
	private ICategoryRepository categoryRepository= new CategoryRepository();
	
	@Override
	public Category findCategoryById(int id) {
		return categoryRepository.findCategoryById(id);
	}

	@Override
	public List<Category> findCategories() {
		return categoryRepository.findCategories();
	}

}
