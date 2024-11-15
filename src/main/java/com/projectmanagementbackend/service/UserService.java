package com.projectmanagementbackend.service;

import com.projectmanagementbackend.Entities.Users;

public interface UserService {
Users addUser(Users user);
boolean authenticateUser(String emailAddress,String password);

}
