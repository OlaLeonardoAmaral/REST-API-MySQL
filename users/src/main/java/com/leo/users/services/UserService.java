package com.leo.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leo.users.entities.User;
import com.leo.users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Page<User> listAllUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public Page<User> getUser(Pageable pageable, Integer id) {
		return userRepository.searchById(pageable, id);
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}
