package com.proje.model.queries;

public class UserQueries {
	
	public static final String saveUserQuery="INSERT INTO user(userId, firstName, lastName, birthOfDate, userName) VALUES(?,?,?,?)";
	
	public static final String saveUser_ProductQuery="INSERT INTO user_product(userId, productId) VALUES(?,?) ";
	
	public static final String updateUserQuery="UPDATE user SET firstName=?, lastName=?, birthOfDate=?, userName=? WHERE userId=?";
		
	public static final String deleteUser_ProductQuery="DELETE FROM user_product WHERE userId=?";
	public static final String deleteUserQuery="DELETE FROM user WHERE userId=?";
	
	public static final String findUserById="SELECT *FROM user WHERE usertId=?";
	
	public static final String findUser="SELECT *FROM user";
	
	public static final String findUserProductQuery="SELECT u.username, u.firstName, u.lastName,"
							+ " p.productName, p.unitPrice, p.avaible, c.categoryName, b.brandName "
							+ "FROM user u  LEFT OUTER JOIN user_product up ON(u.userId=up.userId) "
							+ "LEFT JOIN product p ON(up.productId=p.productId) "
							+ "LEFT JOIN category c ON(p.categoryId=c.categoryId) "
							+ "LEFT JOIN brand b ON(p.brandId=b.brandId) WHERE u.userId=?";
}
