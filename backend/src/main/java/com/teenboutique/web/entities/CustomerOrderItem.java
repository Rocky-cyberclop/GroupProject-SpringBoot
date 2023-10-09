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
	}

}
