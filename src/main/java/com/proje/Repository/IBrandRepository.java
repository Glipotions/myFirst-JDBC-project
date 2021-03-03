package com.proje.Repository;

import java.util.List;

import com.proje.model.Brand;

public interface IBrandRepository {
	Brand findBrandById(int id);
	
	List<Brand> findBrands();
}
