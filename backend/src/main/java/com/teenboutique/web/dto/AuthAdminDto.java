package com.teenboutique.web.dto;

public class AuthAdminDto {
	private String id;
	private String pass;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public AuthAdminDto() {
		super();
	}
	
}
