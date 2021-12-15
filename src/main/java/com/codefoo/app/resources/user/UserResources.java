package com.codefoo.app.resources.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefoo.app.exception.specific.EmailExistException;
import com.codefoo.app.exception.specific.UserExistException;
import com.codefoo.app.exception.specific.UserFieldValidationException;
import com.codefoo.app.model.user.User;
import com.codefoo.app.model.user.UserDTO;
import com.codefoo.app.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserResources {

	private UserService userService;

	@Autowired
	public UserResources(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult theBinding)
			throws UserExistException, EmailExistException, UserFieldValidationException {
		if (theBinding.hasErrors()) {
			List<ObjectError> errors = theBinding.getAllErrors();
			for (ObjectError error : errors) {
				throw new UserFieldValidationException(error.getDefaultMessage());
			}
		}
		User user = this.userService.register(userDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public String helloWorld() {
		return "Hello world";
	}
}
