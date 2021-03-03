package com.proje.Service.Implements;

import java.util.List;

import com.proje.Repository.IBrandRepository;
import com.proje.Repository.Implements.BrandRepository;
import com.proje.Service.IBrandService;
import com.proje.model.Brand;

public class BrandService implements IBrandService{
	private IBrandRepository brandRepository=new BrandRepository();

	@Override
	public Brand findBrandById(int id) {
		return brandRepository.findBrandById(id);
	}

	@Override
	public List<Brand> findBrands() {
		return brandRepository.findBrands();
	}
	
}
