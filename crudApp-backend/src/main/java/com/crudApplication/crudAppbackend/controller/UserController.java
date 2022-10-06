package com.crudApplication.crudAppbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crudApplication.crudAppbackend.exception.UserNotFoundException;
import com.crudApplication.crudAppbackend.model.User;
import com.crudApplication.crudAppbackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/saveuser")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/allusers")
	List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setfName(newUser.getfName());
					user.setlName(newUser.getlName());
					user.setEmail(newUser.getEmail());
					user.setPassword(newUser.getPassword());
					return userRepository.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id){
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return("User with id "+id+" is deleted successfully...");
	}
}
