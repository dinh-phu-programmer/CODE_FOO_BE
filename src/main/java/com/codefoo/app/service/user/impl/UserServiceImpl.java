package com.codefoo.app.service.user.impl;

import static com.codefoo.app.constant.ExceptionMessageConstant.EMAIL_EXIST_EXCEPTION;
import static com.codefoo.app.constant.ExceptionMessageConstant.USER_NAME_EXIST_EXCEPTION;
import static com.codefoo.app.constant.ExceptionMessageConstant.USER_NOT_FOUND_BY_ID;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codefoo.app.authority.AppUserRole;
import com.codefoo.app.exception.specific.EmailExistException;
import com.codefoo.app.exception.specific.ObjectNotFoundException;
import com.codefoo.app.exception.specific.UserExistException;
import com.codefoo.app.model.user.User;
import com.codefoo.app.model.user.UserDTO;
import com.codefoo.app.model.user.UserPrincipal;
import com.codefoo.app.repo.user.UserRepository;
import com.codefoo.app.service.common.CommonAction;
import com.codefoo.app.service.user.UserService;

@Service("userService")
public class UserServiceImpl extends CommonAction<User, Integer, UserRepository>
		implements UserService, UserDetailsService {
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository jpaRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		super(jpaRepository);
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
		User userEmail = this.jpaRepository.findByEmail(data);
		User userUserName = this.jpaRepository.findByUsername(data);
		UserPrincipal userPrincipal = null;

		if (userEmail != null) {

			userPrincipal = setUserByUserNameOrEmail(userEmail);
		}

		if (userUserName != null) {

			userPrincipal = setUserByUserNameOrEmail(userUserName);
		}
		if (userEmail == null && userUserName == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_BY_ID + ": email or username");
		}

		return userPrincipal;
	}

	private UserPrincipal setUserByUserNameOrEmail(User user) {
		user.setLastLoginDate(LocalDateTime.now());
		this.jpaRepository.save(user);
		return new UserPrincipal(user);
	}

	@Override
	public User register(UserDTO userDTO) throws UserExistException, EmailExistException {
		validateRegisterUser(userDTO);
		// do logic register here
		userDTO.setActive(true);
		userDTO.setNotLocked(true);
		userDTO.setProfileImageUrl("");
		userDTO.setLastLoginDate(null);
		userDTO.setJoinDate(LocalDateTime.now());
		userDTO.setRole(AppUserRole.USER.name());
		userDTO.setAuthorities(AppUserRole.USER.getPermissions());
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//		User user = UserTransferUtils.userDtoToUser(userDTO);
		User user = this.modelMapper.map(userDTO, User.class);
		return super.save(user);
	}

	private boolean validateRegisterUser(UserDTO newUser) throws UserExistException, EmailExistException {
		String newUsername = newUser.getUsername();
		String newEmail = newUser.getEmail();
		User userNameExist = findByUsername(newUsername);
		User emailExist = findByEmail(newEmail);

		if (emailExist != null) {
			throw new EmailExistException(EMAIL_EXIST_EXCEPTION + " " + newEmail);
		}

		if (userNameExist != null) {
			throw new UserExistException(USER_NAME_EXIST_EXCEPTION + " " + newUsername);
		}
		return true;
	}

	@Override
	public User findByUsername(String username) {

		return this.jpaRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {

		return this.jpaRepository.findByEmail(email);
	}

	@Override
	public User update(User updateUser, Integer id) throws ObjectNotFoundException {
		User currentUser = null;
		try {
			currentUser = this.findById(id);
		} catch (ObjectNotFoundException e) {
			
			throw new ObjectNotFoundException(e.getMessage(), User.classType);
		}

		currentUser.setFirstName(updateUser.getFirstName());
		currentUser.setLastName(updateUser.getLastName());
		return this.save(currentUser);
	}
}
