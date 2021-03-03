package com.proje.Repository.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.Repository.ICategoryRepository;
import com.proje.connection.DBConnection;
import com.proje.model.Category;
import com.proje.model.queries.CategoryQueries;

public class CategoryRepository implements ICategoryRepository{

	private final Logger logger=LogManager.getLogger();
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;
	
	public Category findCategoryById(int id) {
		connection=DBConnection.getConnection();
		Category category=null;
		try {
			preparedStatement=connection.prepareStatement(CategoryQueries.findCategoryByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int categoryId=resultSet.getInt("categoryId");
				String categoryName=resultSet.getString("categoryName");
				category=new Category(categoryId, categoryName);
			}
			
		} catch (SQLException e) {
			logger.warn("Marka aranýrken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return category;
	}

	@Override
	public List<Category> findCategories() {
		connection=DBConnection.getConnection();
		List<Category>categories=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement(CategoryQueries.findCategoriesQuery);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int categoryId=resultSet.getInt("categoryId");
				String categoryName=resultSet.getString("categoryName");
				
				
				Category category=new Category(categoryId, categoryName);
				categories.add(category);
			}
		} catch (SQLException e) {
			logger.warn("Kategori bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return categories;
	}

}
