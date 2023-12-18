package com.teenboutique.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.teenboutique.web.entities.Employee;

public class EmployeeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	private String phone;

	private String address;

	private String img;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	private boolean gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate starting_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate resigning_date;

	private String role;

	public EmployeeDto() {
		super();
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public LocalDate getStarting_date() {
		return starting_date;
	}

	public void setStarting_date(LocalDate starting_date) {
		this.starting_date = starting_date;
	}

	public LocalDate getResigning_date() {
		return resigning_date;
	}

	public void setResigning_date(LocalDate resigning_date) {
		this.resigning_date = resigning_date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void ConvertEmployeeToDto(Employee e) {
		this.id = e.getId();
		this.name = e.getName();
		this.email = e.getEmail();
		this.phone = e.getPhone();
		this.address = e.getAddress();
		this.img = e.getImg();
		this.dob = e.getDob();
		this.gender = e.isGender();
		this.starting_date = e.getStarting_date();
		this.resigning_date = e.getResigning_date();
		this.role = e.getRole().getId().toString();
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", img=" + img + ", dob=" + dob + ", gender=" + gender +  ", starting_date=" + starting_date +
				", resigning_date=" + resigning_date + ", role=" + role + "]";
	}

}
