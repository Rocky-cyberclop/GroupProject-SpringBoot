package com.teenboutique.web.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CustomerOrder")
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Payment code is required")
    @Size(max = 255, message = "Payment code is too long")
	private String payment_code;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
	@Positive(message = "Total must be a positive value")
	private long total;
	
	@NotBlank(message = "Status is required")
    @Size(max = 255, message = "Status is too long")
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer_order", orphanRemoval = true)
	private List<CustomerOrderItem> order_items = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CustomerOrderItem> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<CustomerOrderItem> order_items) {
		this.order_items = order_items;
	}

	public CustomerOrder() {
		super();
	}

}
