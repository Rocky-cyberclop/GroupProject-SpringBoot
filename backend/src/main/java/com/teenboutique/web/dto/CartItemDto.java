package com.teenboutique.web.dto;

public class CartItemDto {
    private Long product_id;
    private Long size_id;
    private Long customer_id;
    private int quantity;
    private String name;
	private long price;
	
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
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public CartItemDto() {
		super();
	}
	
	public CartItemDto(Long product_id, Long size_id, Long customer_id, int quantity, String name, long price) {
		super();
		this.product_id = product_id;
		this.size_id = size_id;
		this.customer_id = customer_id;
		this.quantity = quantity;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CartItemDto [product_id=" + product_id + ", size_id=" + size_id + ", customer_id=" + customer_id
				+ ", quantity=" + quantity + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
}

