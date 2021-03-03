package com.proje.Service;

import java.util.List;

import com.proje.model.User;

public interface IUserService {
	
	User saveUser(User user);
	
	boolean saveUserProduct(int userId,int productId);
	
	User updateUser(User user);
	
	boolean removeUser(int id);
	
	User findUserById(int id);
	
	User findUserProductById(int id);
	
	List<User> findUsers();
	
	
}
