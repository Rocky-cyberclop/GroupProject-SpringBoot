package com.teenboutique.web.dto;

import java.util.List;

public class ProductEditDto {
	private ProductDto productDto;
	private List<CategoryDto> categoryDtos;
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	public List<CategoryDto> getCategoryDtos() {
		return categoryDtos;
	}
	public void setCategoryDtos(List<CategoryDto> categoryDtos) {
		this.categoryDtos = categoryDtos;
	}
	public ProductEditDto() {
		super();
	}
	public ProductEditDto(ProductDto productDto, List<CategoryDto> categoryDtos) {
		super();
		this.productDto = productDto;
		this.categoryDtos = categoryDtos;
	}
	

}
