package com.codefoo.app.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserUpdateDTO {
	@NotNull(message = "Id is required")
	private Integer id;
	@Length(min = 2, max = 50, message = "First Name required at least {min} character to {max} character")
	@NotNull(message = "First Name is required")
	@NotEmpty(message = "First Name is required")
	private String firstName;
	@Length(min = 2, max = 50, message = "Last Name required at least {min} character to {max} character")
	@NotNull(message = "Last Name is required")
	@NotEmpty(message = "Last Name is required")
	private String lastName;

	private String profileImageUrl;

	public UserUpdateDTO() {
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

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

}
