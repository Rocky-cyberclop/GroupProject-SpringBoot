package com.teenboutique.web.dto;

import java.io.Serializable;

import com.teenboutique.web.entities.ProductDetail;

public class ProductDetailDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long product_id;
	
	private SizeDto size;
	
	private int quantity;	

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public SizeDto getSize() {
		return size;
	}

	public void setSize(SizeDto size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDetailDto() {
		super();
	}

	public ProductDetailDto(ProductDetail pd) {
		super();
		this.product_id = pd.getProduct().getId();
		this.size = new SizeDto(pd.getSize());
		this.quantity = pd.getInventory();
	}
	
	

}
