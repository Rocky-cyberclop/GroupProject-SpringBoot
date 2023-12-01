package com.teenboutique.web.dto;

import java.util.List;

import com.teenboutique.web.entities.Role;

public class EmployeeAndRoles {
	private EmployeeDto employeeDto;
	private List<Role> roles;
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public EmployeeAndRoles() {
		super();
	}
	public EmployeeAndRoles(EmployeeDto employeeDto, List<Role> roles) {
		super();
		this.employeeDto = employeeDto;
		this.roles = roles;
	}
	

}
