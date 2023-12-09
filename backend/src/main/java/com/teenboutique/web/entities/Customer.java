package com.teenboutique.web.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Tên không được để trống")
	private String name;

	@Email(message = "Email không hợp lệ")
	@Size(min = 10, max = 50, message = "Email Phải Từ 10 Đến 50 Ký Tự")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Số Điện Thoại Phải Có 10 Số")



	private String phone;
	
	@NotBlank(message="Địa chỉ không được để trống")
	private String address;
	

	@Past(message = "Ngày Sinh Phải Ở Quá Khứ")

	private LocalDate dob;
	
	//@AssertTrue(message = "Giới tính phải là nam hoặc nữ")
	private boolean gender;
	
	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
	private String password;
	
	private String avatar;
	private boolean locked;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
	@JsonIgnore
	private List<CustomerOrder> orders = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
	@JsonIgnore
	private List<CartItem> cart_items = new ArrayList<>();

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

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

	public List<CartItem> getCart_items() {
		return cart_items;
	}

	public void setCart_items(List<CartItem> cart_items) {
		this.cart_items = cart_items;
	}

	public Customer() {
		super();
	}



}
