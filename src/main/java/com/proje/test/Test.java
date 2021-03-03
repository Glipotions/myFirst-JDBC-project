package com.proje.test;

import java.util.List;

import com.proje.Service.IBrandService;
import com.proje.Service.ICategoryService;
import com.proje.Service.IProductService;
import com.proje.Service.IUserService;
import com.proje.Service.Implements.BrandService;
import com.proje.Service.Implements.CategoryService;
import com.proje.Service.Implements.ProductService;
import com.proje.Service.Implements.UserService;
import com.proje.model.Brand;

public class Test {
	public static void main(String[] args) {
		
		IProductService productService=new ProductService();
		
		IBrandService brandService=new BrandService();
		
		IUserService userService=new UserService();
		
		ICategoryService categoryService=new CategoryService();
		
		List<Brand> brands=brandService.findBrands();
		
		for (Brand brand : brands) {
			System.out.println("Brand id: "+brand.getBrandId()+" - Brand Adý: "+brand.getBrandName());
		}
		
		
	}
}
