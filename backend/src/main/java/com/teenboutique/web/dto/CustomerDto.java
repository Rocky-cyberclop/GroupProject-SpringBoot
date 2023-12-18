package com.teenboutique.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.teenboutique.web.entities.Customer;

public class CustomerDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	private boolean gender;
	
	private String password;
	
	private String avatar;
	
	private boolean locked;
    
	public CustomerDto(Long id, String name, String email, String phone, String address, LocalDate dob,
              boolean gender, String avatar, boolean locked) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.avatar = avatar;
		this.locked = locked;
    }

	public Long getId() {
	return id;
	}
	
	public void setId(Long id) {
	this.id = id;
	}
	
	public String getName() {
	return name;
	}
	
	public void setName(String name) {
	this.name = name;
	}
	
	public String getEmail() {
	return email;
	}
	
	public void setEmail(String email) {
	this.email = email;
	}
	
	public String getPhone() {
	return phone;
	}
	
	public void setPhone(String phone) {
	this.phone = phone;
	}
	
	public String getAddress() {
	return address;
	}
	
	public void setAddress(String address) {
	this.address = address;
	}
	
	public LocalDate getDob() {
	return dob;
	}
	
	public void setDob(LocalDate dob) {
	this.dob = dob;
	}
	
	public boolean isGender() {
	return gender;
	}
	
	public void setGender(boolean gender) {
	this.gender = gender;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAvatar() {
	return avatar;
	}
	
	public void setAvatar(String avatar) {
	this.avatar = avatar;
	}
	
	public boolean isLocked() {
	return locked;
	}
	
	public void setLocked(boolean locked) {
	this.locked = locked;
	}
	
	public void ConvertCustomerToDto(Customer customer) {
         this.id = customer.getId();
         this.name = customer.getName();
         this.email = customer.getEmail();
         this.phone = customer.getPhone();
         this.address = customer.getAddress();
         this.dob = customer.getDob();
         this.gender = customer.isGender();
         this.avatar = customer.getAvatar();
         this.locked = customer.isLocked();
    }
	
	@Override
	public String toString() {
	        return "CustomerDto{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", email='" + email + '\'' +
	                ", phone='" + phone + '\'' +
	                ", address='" + address + '\'' +
	                ", dob=" + dob +
	                ", gender=" + gender +
	                ", avatar='" + avatar + '\'' +
	                ", locked=" + locked +
	                '}';
	}
}
