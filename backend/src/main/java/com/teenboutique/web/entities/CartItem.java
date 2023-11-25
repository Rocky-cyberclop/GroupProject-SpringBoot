package com.teenboutique.web.entities;

import com.teenboutique.web.entities.ProductDetail.ProductDetailId;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "CartItem")
public class CartItem {
	@EmbeddedId
	private CartItemId id;
	
	//extra column
	@Min(value = 1, message = "Số lượng phải lớn hơn 1")
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("customer_id")
	@JoinColumn(name = "customer_id", insertable=false, updatable=false)
	private Customer customer;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("product_detail_id")
	@JoinColumns({
		@JoinColumn(name = "product_id"),
		@JoinColumn(name = "size_id")
	})
	private ProductDetail product_detail;
	
	
	@Embeddable
	public static class CartItemId {
		private ProductDetailId product_detail_id;
		private Long customer_id;
		public ProductDetailId getProduct_detail_id() {
			return product_detail_id;
		}
		public void setProduct_detail_id(ProductDetailId product_detail_id) {
			this.product_detail_id = product_detail_id;
		}
		public Long getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(Long customer_id) {
			this.customer_id = customer_id;
		}
		public CartItemId() {
			super();
		}
		
	}


	public CartItemId getId() {
		return id;
	}


	public void setId(CartItemId id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ProductDetail getProduct_detail() {
		return product_detail;
	}


	public void setProduct_detail(ProductDetail product_detail) {
		this.product_detail = product_detail;
	}


	@Override
	public String toString() {
		return "CartItem [id=" + id + ", quantity=" + quantity + ", customer=" + customer + ", product_detail="
				+ product_detail + "]";
	}
}
