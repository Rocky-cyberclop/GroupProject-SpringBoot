package com.teenboutique.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.entities.ProductDetail;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Positive;

public class OrderDetailAccountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	private int quantity;
	
	private String rate_date;
	
	private String rate_content;

	private int point;

	private long price;

	private String size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRate_date() {
		return rate_date;
	}

	public void setRate_date(String rate_date) {
		this.rate_date = rate_date;
	}

	public String getRate_content() {
		return rate_content;
	}

	public void setRate_content(String rate_content) {
		this.rate_content = rate_content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public OrderDetailAccountDto(CustomerOrderItem customerOrderItem) {
		super();
		this.name = customerOrderItem.getProduct_detail().getProduct().getName();
		this.quantity = customerOrderItem.getQuantity();
		this.rate_date = customerOrderItem.getRate_date()!=null ? customerOrderItem.getRate_date().toString() : null;
		this.rate_content = customerOrderItem.getRate_content();
		this.point = customerOrderItem.getPoint();
		this.price = customerOrderItem.getPrice();
		this.size = customerOrderItem.getProduct_detail().getSize().getName();
	}
	
	

}
