package com.jbc.util.controller.url;

public enum EmployeeUrlUtil {

	CREATE_EMPLOYEE, UPDATE_EMPLOYEE, UPDATE_EMPLOYEE_DEPARTMENT, DELETE_EMPLOYEE, GET_EMPLOYEES, GET_EMPLOYEE;

	@Override
	public String toString() {
		String uri = "http://localhost:8080/employee";
		switch (this) {
		case CREATE_EMPLOYEE:
			return uri + "/create-employee";
		case UPDATE_EMPLOYEE:
			return uri + "/update-employee";
		case UPDATE_EMPLOYEE_DEPARTMENT:
			return uri + "/update-employee-department";
		case DELETE_EMPLOYEE:
			return uri + "/delete-employee";
		case GET_EMPLOYEES:
			return uri + "/get-employees";
		case GET_EMPLOYEE:
			return uri + "/get-employee";
		default:
			return super.toString();
		}
	}

}
