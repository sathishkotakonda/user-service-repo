package com.ecom.user.GlobalExceptions;

public class InvalidUserCredentialsExceptionhandler extends RuntimeException  {

	
	
	public  InvalidUserCredentialsExceptionhandler (String message) {
		
		super(message); // here we are storing a message in super class runtimeException  and we are using this in global exception
	}
	
}
