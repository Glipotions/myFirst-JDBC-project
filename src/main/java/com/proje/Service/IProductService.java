package com.proje.Service;

import java.util.List;

import com.proje.model.Product;

public interface IProductService {
	
	Product saveProduct(Product product);
	
	boolean saveBatchProduct(List<Product> products);
	
	Product updateProduct(Product product);
	
	boolean removeProduct(int id);
	
	Product findProductById(int id);
	
	List<Product> findProducts(); 
	
}
