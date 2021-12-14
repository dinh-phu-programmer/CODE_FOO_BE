package com.codefoo.app.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefoo.app.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findByUsername(String username);
}
