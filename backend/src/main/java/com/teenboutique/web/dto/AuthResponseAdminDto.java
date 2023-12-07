package com.teenboutique.web.dto;

public class AuthResponseAdminDto {
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public AuthResponseAdminDto() {
		super();
	}
	
}
