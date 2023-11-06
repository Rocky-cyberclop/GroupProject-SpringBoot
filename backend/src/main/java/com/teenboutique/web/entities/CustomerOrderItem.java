package com.teenboutique.web.entities;

import java.time.LocalDate;

import com.teenboutique.web.entities.ProductDetail.ProductDetailId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "CustomerOrderItem")
public class CustomerOrderItem {
	@EmbeddedId
	private CustomerOrderItemId id;
	
	//extra column
	private int quantity;
	private LocalDate rate_date;
	@Column(length = 5000)
	private String rate_content;
	private int point;
	private long price;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("customer_order_id")
	@JoinColumn(name = "customer_order_id", insertable=false, updatable=false)
	private CustomerOrder customer_order;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("product_detail_id")
	@JoinColumns({
		@JoinColumn(name = "product_id"),
		@JoinColumn(name = "size_id")
	})
	private ProductDetail product_detail;
	
	
	@Embeddable
	public static class CustomerOrderItemId {
		private ProductDetailId product_detail_id;
		private Long customer_order_id;
		public ProductDetailId getProduct_detail_id() {
			return product_detail_id;
		}
		public void setProduct_detail_id(ProductDetailId product_detail_id) {
			this.product_detail_id = product_detail_id;
		}
		public Long getCustomer_order_id() {
			return customer_order_id;
		}
		public void setCustomer_order_id(Long customer_order_id) {
			this.customer_order_id = customer_order_id;
		}
		
		
	}


	public CustomerOrderItemId getId() {
		return id;
	}

	public void setId(CustomerOrderItemId id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getRate_date() {
		return rate_date;
	}

	public void setRate_date(LocalDate rate_date) {
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

	public CustomerOrder getCustomer_order() {
		return customer_order;
	}

	public void setCustomer_order(CustomerOrder customer_order) {
		this.customer_order = customer_order;
	}

	public ProductDetail getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(ProductDetail product_detail) {
		this.product_detail = product_detail;
	}

}
