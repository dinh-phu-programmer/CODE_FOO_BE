package com.codefoo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codefoo.app.exception.specific.EmailExistException;
import com.codefoo.app.exception.specific.ObjectNotFoundException;
import com.codefoo.app.exception.specific.UserExistException;
import com.codefoo.app.exception.specific.UserFieldValidationException;
import com.codefoo.app.exception.specific.UserNotFoundException;
import com.codefoo.app.response.HttpResponse;

@RestControllerAdvice
public class MainExceptionHandler {
	// Not Found
	@ExceptionHandler(value = { UserNotFoundException.class, ObjectNotFoundException.class })
	public ResponseEntity<HttpResponse> userNotFoundException(Exception e) {
		return createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
	}

//	// Not Found
//	@ExceptionHandler(value = { ObjectNotFoundException.class })
//	public ResponseEntity<HttpResponse> objectNotFound(ObjectNotFoundException e) {
//		return createHttpResponse(HttpStatus.NOT_FOUND, e.getMessage());
//	}

	// Bad Request
	@ExceptionHandler(value = { UserFieldValidationException.class, EmailExistException.class,
			UserExistException.class })
	public ResponseEntity<HttpResponse> errorFieldException(Exception e) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	public ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		HttpResponse response = new HttpResponse(httpStatus.value(), httpStatus, message,
				httpStatus.getReasonPhrase().toUpperCase());
		return new ResponseEntity<>(response, httpStatus);
	}
}
