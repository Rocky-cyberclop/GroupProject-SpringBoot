package com.teenboutique.web.dto;

import java.util.List;

public class OrdersDto {
	private List<OrderDto> orderDtos;
	private int currentPage;
	private int totalPage;
	public List<OrderDto> getOrderDtos() {
		return orderDtos;
	}
	public void setOrderDtos(List<OrderDto> orderDtos) {
		this.orderDtos = orderDtos;
	}
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
	public OrdersDto() {
		super();
	}
	public OrdersDto(List<OrderDto> orderDtos, int currentPage, int totalPage) {
		super();
		this.orderDtos = orderDtos;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}
	
}
