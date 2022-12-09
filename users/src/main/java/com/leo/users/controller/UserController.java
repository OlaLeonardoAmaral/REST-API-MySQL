package com.leo.users.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.users.entities.User;
import com.leo.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public Page<User> list(Pageable pageable) {
		return userService.listAllUser(pageable);
	}
	
	@GetMapping("/{firstName}")
	public ResponseEntity<Page<User>> get(@PathVariable String firstName, Pageable pageable) {
		try {
			Page<User> user = userService.getUserByName(pageable, firstName);
			return new ResponseEntity<Page<User>>(user, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Page<User>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public void add(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		try {
			// User existUser = userService.getUser(id);
			user.setId(id);
			userService.saveUser(user);
			return new ResponseEntity<>(HttpStatus.OK); 
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Page<User>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
}
