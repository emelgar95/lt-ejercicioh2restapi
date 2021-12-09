package com.h2restapi.ejercicioh2restapi.entity;

import java.util.List;

public class PositionSearch {
	private long id;
	private String name;
	private List<EmployeeSearchPosition> employee;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EmployeeSearchPosition> getEmployee() {
		return employee;
	}
	public void setEmployee(List<EmployeeSearchPosition> employee) {
		this.employee = employee;
	}
	
}
