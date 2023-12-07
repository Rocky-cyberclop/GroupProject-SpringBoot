package com.teenboutique.web.dto;

import java.io.Serializable;
import java.util.List;

public class ProductsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int totalPage;
	private List<ProductDto> productDtos;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<ProductDto> getProductDtos() {
		return productDtos;
	}
	public void setProducts(List<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}
	public ProductsDto() {
		super();
	}

}
