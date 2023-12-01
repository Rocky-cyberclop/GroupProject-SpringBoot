package com.teenboutique.web.dto;

import java.io.Serializable;
import java.util.List;

import com.teenboutique.web.entities.Employee;

public class EmployeesDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int totalPage;
	private List<Employee> employees;
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public EmployeesDto() {
		super();
	}
	
	
}
