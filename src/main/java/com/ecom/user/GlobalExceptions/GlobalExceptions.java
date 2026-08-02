package com.ecom.user.GlobalExceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler
	public ResponseEntity<Object> handleExceptions(Exception ex) {
		Map<String, String> resmap = new HashMap<String, String>();
		resmap.put("message", ex.getMessage());
		resmap.put("Status", "failed");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resmap);

	}

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handleApiValidationExceptions(MethodArgumentNotValidException exc) {
		Map<String, String> errors = new HashMap<String, String>();

		exc.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("message", "unable to process your request");
		response.put("Status", "failed");
		response.put("error", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}

	@ExceptionHandler(InvalidUserCredentialsExceptionhandler.class)
	public ResponseEntity<Map<String, Object>> InvalidUserCredentials(InvalidUserCredentialsExceptionhandler message) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("Result", "failed");
		map.put("Body", message.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	}

}
