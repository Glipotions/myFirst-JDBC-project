package com.proje.Service.Implements;

import java.util.List;

import com.proje.Repository.IProductRepository;
import com.proje.Repository.Implements.ProductRepostitory;
import com.proje.Service.IProductService;
import com.proje.model.Product;

public class ProductService implements IProductService{
	private IProductRepository productRepository=new ProductRepostitory();

	@Override
	public Product saveProduct(Product product) {
		return productRepository.saveProduct(product);
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		return productRepository.saveBatchProduct(products);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.updateProduct(product);
	}

	@Override
	public boolean removeProduct(int id) {
		return productRepository.removeProduct(id);
	}

	@Override
	public Product findProductById(int id) {
		return productRepository.findProductById(id);
	}

	@Override
	public List<Product> findProducts() {
		return productRepository.findProducts();
	}
}
