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
			logger.warn("database.properties dosyas�ndan verileri �ekerken bir hata meydana geldi..!"+e);
		}
		
		
		
		
	}

	public static Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName(driver);
			logger.info("MySql Driver ba�ar�l� bir �ekilde bulundu");
		} catch (ClassNotFoundException e) {
			logger.warn("MySql Driver bulunamad�. Hata: "+e);
			return null;
			
		}
		
		try {
			connection=DriverManager.getConnection(url, username, password);
		 
		} catch (SQLException e) {
			logger.warn("Veritaban�na ba�lan�rken sorun ya�and�: Hata: "+e);
			return null;
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection,PreparedStatement preparedStatement, ResultSet resultSet) {
		if(connection!=null) {
			try {
				connection.close();
				logger.info("MySql ba�ar�l� bir �ekilde kapat�ld�");
			} catch (SQLException e) {
				logger.warn("MySql ba�lant� kapat�l�rken sorun ya�and�: Hata: "+e);
			}
		}
		
		if(preparedStatement!=null) {
			try{
				preparedStatement.close();
				logger.info("PreparedStatement ba�ar�l� bir �ekilde kapat�ld�");
			}
			catch(SQLException e) {
				logger.warn("PreparedStatement kapat�l�rken bir hata ger�ekle�ti!!");
			}
		}
		
		if(resultSet!=null) {
			try{
				resultSet.close();
				logger.info("resultSet ba�ar�l� bir �ekilde kapat�ld�");
			}
			catch(SQLException e) {
				logger.warn("resultSet kapat�l�rken bir hata ger�ekle�ti!!");
			}
		}
		 
	}
}
