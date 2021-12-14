package com.codefoo.app.service.user;

import com.codefoo.app.exception.specific.EmailExistException;
import com.codefoo.app.exception.specific.UserExistException;
import com.codefoo.app.model.user.User;
import com.codefoo.app.model.user.UserDTO;
//import com.codefoo.app.service.common.CommonService;

public interface UserService {//extends CommonService<User, Integer> {
	User register(UserDTO user) throws UserExistException, EmailExistException;

	User findByUsername(String username);

	User findByEmail(String email);
	
	
}
