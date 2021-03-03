package com.proje.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBConnection {

	private static final Logger logger=LogManager.getLogger();
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
	static {
		Properties properties=new Properties();
		
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("src/main/resources/database.properties");
			properties.load(inputStream);
			driver=properties.getProperty("db_driver");
			driver=properties.getProperty("db_url");
			driver=properties.getProperty("db_username");
			driver=properties.getProperty("db_password");
			
		} catch (IOException e) {
			logger.warn("database.properties dosyasýndan verileri çekerken bir hata meydana geldi..!"+e);
		}
		
		
		
		
	}

	public static Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName(driver);
			logger.info("MySql Driver baþarýlý bir þekilde bulundu");
		} catch (ClassNotFoundException e) {
			logger.warn("MySql Driver bulunamadý. Hata: "+e);
			return null;
			
		}
		
		try {
			connection=DriverManager.getConnection(url, username, password);
		 
		} catch (SQLException e) {
			logger.warn("Veritabanýna baðlanýrken sorun yaþandý: Hata: "+e);
			return null;
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection,PreparedStatement preparedStatement, ResultSet resultSet) {
		if(connection!=null) {
			try {
				connection.close();
				logger.info("MySql baþarýlý bir þekilde kapatýldý");
			} catch (SQLException e) {
				logger.warn("MySql baðlantý kapatýlýrken sorun yaþandý: Hata: "+e);
			}
		}
		
		if(preparedStatement!=null) {
			try{
				preparedStatement.close();
				logger.info("PreparedStatement baþarýlý bir þekilde kapatýldý");
			}
			catch(SQLException e) {
				logger.warn("PreparedStatement kapatýlýrken bir hata gerçekleþti!!");
			}
		}
		
		if(resultSet!=null) {
			try{
				resultSet.close();
				logger.info("resultSet baþarýlý bir þekilde kapatýldý");
			}
			catch(SQLException e) {
				logger.warn("resultSet kapatýlýrken bir hata gerçekleþti!!");
			}
		}
		 
	}
}
