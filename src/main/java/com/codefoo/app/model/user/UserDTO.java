package com.codefoo.app.model.user;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.codefoo.app.validation.EmailConstraint;
import com.codefoo.app.validation.FieldPasswordMatch;

@FieldPasswordMatch.List({
		@FieldPasswordMatch(field = "password", fieldMatch = "confirmPassword", message = "Password doesn't match") })

public class UserDTO {
	private Integer id;
	@Length(min = 2, max = 50, message = "First Name required at least {min} character to {max} character")
	@NotNull(message = "First Name is required")
	@NotEmpty(message = "First Name is required")
	private String firstName;
	@Length(min = 2, max = 50, message = "Last Name required at least {min} character to {max} character")
	@NotNull(message = "Last Name is required")
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	@Length(min = 2, max = 50, message = "Username required at least min: {min} - max :{max}")
	@NotNull(message = "Username is required")
	@NotEmpty(message = "Username is required")
	private String username;
	private String password;
	private String confirmPassword;
	@NotNull(message = "Email is required!")
	@EmailConstraint(message = "Email is invalid")
	private String email;
	private String profileImageUrl;
	private LocalDateTime lastLoginDate;
	private LocalDateTime joinDate;
	private String role;
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;

	public UserDTO() {
	}

	public UserDTO(String firstName, String lastName, String username, String password, String email,
			String profileImageUrl, LocalDateTime lastLoginDate, LocalDateTime joinDate, String role,
			String[] authorities, boolean isActive, boolean isNotLocked) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
	}

	public UserDTO(Integer id, String firstName, String lastName, String username, String password, String email,
			String profileImageUrl, LocalDateTime lastLoginDate, LocalDateTime joinDate, String role,
			String[] authorities, boolean isActive, boolean isNotLocked) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
	}

	public UserDTO(@Length(min = 2, max = 10, message = "{name.not.null}") String firstName, String lastName,
			String username, String password, String confirmPassword,
			@NotNull(message = "{email.not.null}") String email, String profileImageUrl, LocalDateTime lastLoginDate,
			LocalDateTime joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean notLocked) {
		isNotLocked = notLocked;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email + '\''
				+ ", profileImageUrl='" + profileImageUrl + '\'' + ", lastLoginDate=" + lastLoginDate + ", joinDate="
				+ joinDate + ", role='" + role + '\'' + ", isActive=" + isActive + ", isNotLocked=" + isNotLocked + '}';
	}
}
