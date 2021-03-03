package com.proje.Repository.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.proje.Repository.IBrandRepository;
import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.queries.BrandQueries;

public class BrandRepository implements IBrandRepository{

	private final Logger logger=LogManager.getLogger();
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;

	public Brand findBrandById(int id) {
		connection=DBConnection.getConnection();
		Brand brand=null;
		try {
			preparedStatement=connection.prepareStatement(BrandQueries.findBrandByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet =preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int brandId=resultSet.getInt("userId");
				String brandName=resultSet.getString("brandName");
				brand=new Brand(brandId, brandName);
			}
			
		} catch (SQLException e) {
			logger.warn("Marka aranýrken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return brand;
	}

	@Override
	public List<Brand> findBrands() {
		connection=DBConnection.getConnection();
		List<Brand>brands=new ArrayList<>();
		try {
			preparedStatement=connection.prepareStatement(BrandQueries.findBrandsQuery);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int brandId=resultSet.getInt("userId");
				String brandName=resultSet.getString("brandName");
				
				
				Brand brand=new Brand(brandId, brandName);
				brands.add(brand);
			}
		} catch (SQLException e) {
			logger.warn("Marka bulunurken bir hata meydana geldi.. Hata: "+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return brands;
	}

}
