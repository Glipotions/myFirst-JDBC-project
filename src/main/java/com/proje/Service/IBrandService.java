package com.proje.Service;

import java.util.List;

import com.proje.model.Brand;

public interface IBrandService {
	Brand findBrandById(int id);
	
	List<Brand> findBrands();
}
