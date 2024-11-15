package com.projectmanagementbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectmanagementbackend.Entities.Users;
import com.projectmanagementbackend.Repository.UserRepository;
import com.projectmanagementbackend.config.EmailAlreadyExistsException;
import com.projectmanagementbackend.config.UserNotFoundException;
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManger;
	@Autowired
	public UserServiceImpl(UserRepository userRepo,PasswordEncoder passwordEncoder,AuthenticationManager authManger){
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
		this.authManger=authManger;
	}
	@Override
	public Users addUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		user.setDisplayName(user.getFirstName()+" "+user.getLastName());
		 if (userRepo.findByEmailAddress(user.getEmailAddress()) != null) {
			 throw new EmailAlreadyExistsException("Email address already exists");
		    }
		return userRepo.save(user);
	}
	 @Override
	    public boolean authenticateUser(String emailAddress, String password) {
	       
		 Users user = userRepo.findByEmailAddress(emailAddress);
		 if (user == null) {
	            throw new UserNotFoundException("User not found");
	        }
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true; 
        }
		return false;
	    }
	}

	
	
