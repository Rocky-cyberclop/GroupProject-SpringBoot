package com.teenboutique.web.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Tên không được trống")
	private String name;
	
	@Email(message = "Email không hợp lệ")
	private String email;
	
	@NotBlank(message = "Số điện thoại không được trống")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại không hợp lệ")
	private String phone;
	
	@NotBlank(message = "Địa chỉ không được trống")
	private String address;
	
	private String img;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	private boolean gender;
	
	@Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
	private String password;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate starting_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate resigning_date;
	
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true)
	private List<CustomerOrder> orders = new ArrayList<>();

	public Employee() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

}
