package com.teenboutique.web.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "ProductDetail")
public class ProductDetail {
	@EmbeddedId
	private ProductDetailId id;
	
	//extra column
	@Positive(message = "Inventory must be a positive value")
	private int inventory;

	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("product_id")
	@JoinColumn(name = "product_id", insertable=false, updatable=false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("size_id")
	@JoinColumn(name = "size_id", insertable=false, updatable=false)
	private Size size;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product_detail", orphanRemoval = true)
	private List<CartItem> cart_items = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product_detail", orphanRemoval = true)
	private List<CustomerOrderItem> order_items = new ArrayList<>();
	
	@Embeddable
	public static class ProductDetailId{
		public ProductDetailId() {
			
		}
		public ProductDetailId(Long product_id, Long size_id) {
			this.product_id=product_id;
			this.size_id=size_id;
		}
		public Long getProduct_id() {
			return product_id;
		}
		public void setProduct_id(Long product_id) {
			this.product_id = product_id;
		}
		public Long getSize_id() {
			return size_id;
		}
		public void setSize_id(Long size_id) {
			this.size_id = size_id;
		}
		private Long product_id;
		private Long size_id;
	}

	public ProductDetailId getId() {
		return id;
	}

	public void setId(Long product_id, Long size_id) {
		this.id = new ProductDetailId(product_id, size_id);
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public List<CartItem> getCart_items() {
		return cart_items;
	}

	public void setCart_items(List<CartItem> cart_items) {
		this.cart_items = cart_items;
	}

	public List<CustomerOrderItem> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<CustomerOrderItem> order_items) {
		this.order_items = order_items;
	}

	public ProductDetail() {
		super();
	}
	

}
