package com.proje.Repository.Implements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.Repository.IUserRepository;
import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.User;
import com.proje.model.queries.UserQueries;

public class UserRepository implements IUserRepository{
	
	private final Logger logger=LogManager.getLogger();
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;
	
	public User saveUser(User user) {
		connection=DBConnection.getConnection();
		//"INSERT INTO user(userId, firstName, lastName, birthOfDate, userName) VALUES(?,?,?,?)";
		try {
			preparedStatement=connection.prepareStatement(UserQueries.saveUserQuery);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setDate(4, (Date) user.getBirthOfDate());
			preparedStatement.setString(5, user.getUserName());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Kiþi kaydederken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		connection=DBConnection.getConnection();
		//INSERT INTO user_product(userId, productId) VALUES(?,?) 
		try {
			preparedStatement=connection.prepareStatement(UserQueries.saveUser_ProductQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, productId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Kiþiye ürün kaydederken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

	@Override
	public User updateUser(User user) {
		connection=DBConnection.getConnection();
		//"UPDATE user SET firstName=?, lastName=?, birthOfDate=?, userName=? WHERE userId=?";
		try {
			preparedStatement=connection.prepareStatement(UserQueries.updateUserQuery);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, (Date) user.getBirthOfDate());
			preparedStatement.setString(4, user.getUserName());
			preparedStatement.setInt(5, user.getUserId());
			
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Kiþi güncellenirken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

	@Override
	public boolean removeUser(int id) {
		//"DELETE FROM user WHERE userId=?";
		connection=DBConnection.getConnection();
		try {
			preparedStatement=connection.prepareStatement(UserQueries.deleteUser_ProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			
			preparedStatement=connection.prepareStatement(UserQueries.deleteUserQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn("Kiþi silinirken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		
		return false;
	}

	@Override
	public User findUserById(int id) {
		//findByUserId="SELECT *FROM user WHERE usertId=?";
		connection=DBConnection.getConnection();
		User user=null;
		try {
			preparedStatement=connection.prepareStatement(UserQueries.findUserById);
			preparedStatement.setInt(1, id);
			resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int userId=resultSet.getInt("userId");
				String firstName=resultSet.getString("firstName");
				String lastName=resultSet.getString("lastName");
				Date birthOfDate=resultSet.getDate("BirthOfDate");
				String userName=resultSet.getString("username");
				
				user =new User(userId, firstName, lastName, birthOfDate, userName);
			}
			
		} catch (SQLException e) {
			logger.warn("Ürün bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}



	@Override
	public List<User> findUsers() {
		connection=DBConnection.getConnection();
		List<User>users=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement(UserQueries.findUser);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int userId=resultSet.getInt("userId");
				String firstName=resultSet.getString("firstName");
				String lastName=resultSet.getString("lastName");
				Date birthOfDate=resultSet.getDate("BirthOfDate");
				String userName=resultSet.getString("username");
				
				User user=new User(userId, firstName, lastName, birthOfDate, userName); 
				
				users.add(user);
			}
		} catch (SQLException e) {
			logger.warn("Kiþi bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return users;
	}

	@Override
	public User findUserProductById(int id) {
		connection=DBConnection.getConnection();
		User user=null;
		try {
			preparedStatement=connection.prepareStatement(UserQueries.findUserProductQuery);
			preparedStatement.setInt(1, id);
			resultSet= preparedStatement.executeQuery();
			
			boolean durum=true;
			List <Product> products=new ArrayList<Product>();
			while(resultSet.next()) {
				
				if(durum) {
					int userId=resultSet.getInt("userId");
					String firstName=resultSet.getString("firstName");
					String lastName=resultSet.getString("lastName");
					Date birthOfDate=resultSet.getDate("BirthOfDate");
					String userName=resultSet.getString("username");
				}
				int productId=resultSet.getInt("productId");
				String productName=resultSet.getString("productName");
				double unitPrice=resultSet.getDouble("unitPrice");
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
				user.setProducts(products);
			
			
			
		} catch (SQLException e) {
			logger.warn("Kiþiye ait Ürün bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
		
	}

	
}
