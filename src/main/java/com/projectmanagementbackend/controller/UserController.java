package com.projectmanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementbackend.Entities.Users;
import com.projectmanagementbackend.service.JwtUtilites;
import com.projectmanagementbackend.service.UserService;

import org.springframework.web.bind.annotation.RequestBody; // Correct import for RequestBody


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	private final JwtUtilites jwtUtilites;
	@Autowired
	public UserController(UserService userService,JwtUtilites jwtUtilites) {
		this.userService=userService;
		this.jwtUtilites=jwtUtilites;
	}
	@PostMapping("/signup")
	public ResponseEntity<Users> signup(@RequestBody Users user) {
		System.out.println("Received User: " + user);
		Users createUser = userService.addUser(user);
		return ResponseEntity.ok(createUser);
		
	}
	 @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody Users user) {
	        // Authenticate the user using email and password
	        boolean isAuthenticated = userService.authenticateUser(user.getEmailAddress(), user.getPassword());
	        
	        if (isAuthenticated) {
	          
	            String token= jwtUtilites.generateToken(user.getEmailAddress());
	            return ResponseEntity.ok("Login successful "+token);
	            
	        } else {
	            return ResponseEntity.status(401).body("Login failed");
	        }
	    }
}
