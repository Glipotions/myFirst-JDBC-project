package com.proje.Repository.Implements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.Repository.IProductRepository;
import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.queries.ProductQueries;

public class ProductRepostitory implements IProductRepository{

	private final Logger logger=LogManager.getLogger();
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;
	
	@Override
	public Product saveProduct(Product product) {
		connection=DBConnection.getConnection();
		//"INSERT INTO product(productId, productName, unitPrice, avaible,addDate, updateDate, categoryId, brandId) VALUES(?,?,?,?,?,?,?,?)"
		try {
			LocalDateTime localDateTime=LocalDateTime.now();
			preparedStatement=connection.prepareStatement(ProductQueries.saveProductQuery);
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
			preparedStatement.setTimestamp(6, null);
			preparedStatement.setInt(7, product.getCategory().getCategoryId());
			preparedStatement.setInt(8, product.getBrand().getBrandId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Ürün kaydederken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return product;
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) {
		
		connection=DBConnection.getConnection();
		//"INSERT INTO product(productId, productName, unitPrice, avaible,addDate, updateDate, categoryId, brandId) VALUES(?,?,?,?,?,?,?,?)"
		try {
			LocalDateTime localDateTime=LocalDateTime.now();
			preparedStatement=connection.prepareStatement(ProductQueries.saveProductQuery);
			
			for(Product product: products) {
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
			preparedStatement.setTimestamp(6, null);
			preparedStatement.setInt(7, product.getCategory().getCategoryId());
			preparedStatement.setInt(8, product.getBrand().getBrandId());
			preparedStatement.addBatch();
			}
			
		} catch (SQLException e) {
			logger.warn("Ürün kaydederken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

	@Override
	public Product updateProduct(Product product) {
		connection=DBConnection.getConnection();
		//"UPDATE user SET firstName=?, lastName=?, birthOfDate=?, userName=? WHERE userId=?";
		try {
			LocalDateTime localDateTime=LocalDateTime.now();
			preparedStatement=connection.prepareStatement(ProductQueries.updateProductQuery);
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			
			preparedStatement.setTimestamp(6, Timestamp.valueOf(localDateTime));
			preparedStatement.setInt(7, product.getCategory().getCategoryId());
			preparedStatement.setInt(8, product.getBrand().getBrandId());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.warn("Ürün güncellenirken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return product;
	}

	@Override
	public boolean removeProduct(int id) {
		//"DELETE FROM product WHERE productId=?";
				connection=DBConnection.getConnection();
				try {
					preparedStatement=connection.prepareStatement(ProductQueries.deleteUser_ProductQuery);
					preparedStatement.setInt(1, id);
					preparedStatement.executeUpdate();
					
					
					preparedStatement=connection.prepareStatement(ProductQueries.deleteProductQuery);
					preparedStatement.setInt(1, id);
					preparedStatement.executeUpdate();
					
				} catch (SQLException e) {
					logger.warn("Ürün silinirken bir hata meydana geldi.. Hata: "+e);
				}
				finally {
					DBConnection.closeConnection(connection, preparedStatement, null);
				}
				
				return false;
	}

	@Override
	public Product findProductById(int id) {
		//findByUserId="SELECT *FROM user WHERE usertId=?";
		connection=DBConnection.getConnection();
		Product product=null;
		try {
			preparedStatement=connection.prepareStatement(ProductQueries.findByProductId);
			preparedStatement.setInt(1, id);
			resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int productId=resultSet.getInt("productId");
				String productName=resultSet.getString("productName");
				Double unitPrice=resultSet.getDouble("unitPrice");
				int avaible=resultSet.getInt("avaible");
				Date addDate=resultSet.getDate("addDate");
				Date updateDate=resultSet.getDate("updateDate");
				int categoryId=resultSet.getInt("categoryId");
				String categoryName=resultSet.getString("categoryName");
				int brandId=resultSet.getInt("brandId");
				String brandName=resultSet.getString("brandName");
				
				Category category=new Category(categoryId, categoryName);
				Brand brand=new Brand(brandId, brandName);
				
				
				product=new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);
			}
			
		} catch (SQLException e) {
			logger.warn("Ürün bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return null;
	}

	@Override
	public List<Product> findProducts() {
		connection=DBConnection.getConnection();
		List<Product>products=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement(ProductQueries.findProduct);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int productId=resultSet.getInt("productId");
				String productName=resultSet.getString("productName");
				Double unitPrice=resultSet.getDouble("unitPrice");
				int avaible=resultSet.getInt("avaible");
				Date addDate=resultSet.getDate("addDate");
				Date updateDate=resultSet.getDate("updateDate");
				int categoryId=resultSet.getInt("categoryId");
				String categoryName=resultSet.getString("categoryName");
				int brandId=resultSet.getInt("brandId");
				String brandName=resultSet.getString("brandName");
				
				Category category=new Category(categoryId, categoryName);
				Brand brand=new Brand(brandId, brandName);
				
				
				Product product=new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);
				
				products.add(product);
			}
		} catch (SQLException e) {
			logger.warn("Ürün bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return null;
	}

}
