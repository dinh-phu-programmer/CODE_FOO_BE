package com.codefoo.app.resources.user;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefoo.app.exception.specific.EmailExistException;
import com.codefoo.app.exception.specific.ObjectNotFoundException;
import com.codefoo.app.exception.specific.UserExistException;
import com.codefoo.app.exception.specific.UserFieldValidationException;
import com.codefoo.app.model.user.User;
import com.codefoo.app.model.user.UserDTO;
import com.codefoo.app.model.user.UserUpdateDTO;
import com.codefoo.app.response.HttpResponse;
import com.codefoo.app.service.user.UserService;
import com.codefoo.app.utils.UserTransferUtils;

@RestController
@RequestMapping("/user")
public class UserResources {

	private UserService userService;
	private ModelMapper modelMapper;

	@Autowired
	public UserResources(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
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

	@PutMapping()
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateDTO userDTO, BindingResult theBinding)
			throws UserFieldValidationException, ObjectNotFoundException {
		if (theBinding.hasErrors()) {
			List<ObjectError> errors = theBinding.getAllErrors();

			for (ObjectError error : errors) {

				throw new UserFieldValidationException(error.getDefaultMessage());
			}
		}
		User updateUser = this.modelMapper.map(userDTO, User.class);

		User result = this.userService.update(updateUser, updateUser.getId());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponse> deleteUser(@PathVariable int id) throws ObjectNotFoundException {
		this.userService.delete(id);
		return new ResponseEntity<>(new HttpResponse(), HttpStatus.OK);
	}

}
