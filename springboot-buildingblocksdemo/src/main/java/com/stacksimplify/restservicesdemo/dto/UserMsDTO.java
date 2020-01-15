package com.stacksimplify.restservicesdemo.dto;

public class UserMsDTO {
	
	private long id;
	private String username;
	private String email;

		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserMsDTO() {

	}

	public UserMsDTO(long id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	

	@Override
	public String toString() {
		return "UserMsDTO [id=" + id + ", username=" + username + ", email=" + email + "]";
	}

	
}
