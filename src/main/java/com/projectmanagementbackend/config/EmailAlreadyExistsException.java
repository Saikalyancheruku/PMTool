package com.projectmanagementbackend.config;

public class EmailAlreadyExistsException extends RuntimeException{
	  public EmailAlreadyExistsException(String message) {
	        super(message);
	    }
}
