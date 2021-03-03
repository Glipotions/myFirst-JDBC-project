package com.proje.Service.Implements;

import java.util.List;

import com.proje.Repository.IUserRepository;
import com.proje.Repository.Implements.UserRepository;
import com.proje.Service.IUserService;
import com.proje.model.User;

public class UserService implements IUserService{
	
	private IUserRepository userRepository=new UserRepository();

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		return userRepository.saveUserProduct(userId, productId);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean removeUser(int id) {
		return userRepository.removeUser(id);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findUserProductById(int id) {
		return userRepository.findUserProductById(id);
	}

	@Override
	public List<User> findUsers() {
		return userRepository.findUsers();
	}
	
	
}
