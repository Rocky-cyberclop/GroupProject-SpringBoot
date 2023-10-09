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

@Entity
@Table(name = "ProductDetail")
public class ProductDetail {
	@EmbeddedId
	private ProductDetailId id;
	
	//extra column
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
		private Long product_id;
		private Long size_id;
	}
	

}
