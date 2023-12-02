package com.teenboutique.web.dto;

import java.time.LocalDate;

public class OrderDto {
	private Long id;
	
	private String payment_code;
	
	private LocalDate date;
	
	private long total;
	
	private String status;
	
	private String employee_id;
	
	private String customer_id;

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

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public OrderDto() {
		super();
	}

	public OrderDto(Long id, String payment_code, LocalDate date, Long total, String status, String employee_id,
			String customer_id) {
		super();
		this.id = id;
		this.payment_code = payment_code;
		this.date = date;
		this.total = total;
		this.status = status;
		this.employee_id = employee_id;
		this.customer_id = customer_id;
	}
	
}
