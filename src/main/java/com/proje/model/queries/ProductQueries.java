package com.proje.model.queries;

public class ProductQueries {
	public static final String saveProductQuery="INSERT INTO product(productId, productName, unitPrice, avaible,addDate, updateDate, categoryId, brandId) VALUES(?,?,?,?,?,?,?,?)";
	
	public static final String updateProductQuery="UPDATE product SET productName=?, unitPrice=?, avaible=?, updateDate=?, categoryId=?, brandId=? WHERE productId=?";
		
	public static final String deleteUser_ProductQuery="DELETE FROM user_product WHERE productId=?";
	public static final String deleteProductQuery="DELETE FROM product WHERE productId=?";
	
	public static final String findByProductId="SELECT *FROM product WHERE productId=?";
	
	public static final String findProduct="SELECT *FROM product";
	
	
	
	
}
