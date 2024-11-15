package com.projectmanagementbackend.service;

import java.util.Map;

public interface JwtUtilites {
 String generateToken(String emailAddress);
 String createToken(Map<String, Object> claims, String subject);
 public String extractEmail(String token);
 public Boolean validateToken(String token, String emailAddress);
}
