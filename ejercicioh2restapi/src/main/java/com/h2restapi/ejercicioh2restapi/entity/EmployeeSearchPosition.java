package com.h2restapi.ejercicioh2restapi.entity;

public class EmployeeSearchPosition{
	private long id;
	private double salary;
	private PersonEntity person;
	
	public PersonEntity getPerson() {
		return person;
	}
	public void setPerson(PersonEntity person) {
		this.person = person;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
