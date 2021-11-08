package com.jbc.util.controller.url;

/*enum that contains all the Department endpoints*/
public enum DepartmentUrlUtil {

	CREATE_DEPARTMENT, UPDATE_DEPARTMENT, DELETE_DEPARTMENT, GET_DEPARTMENTS, ADD_EMPLOYEE, REMOVE_EMPLOYEE,
	GET_DEPARTMENT, GET_DEPARTMENT_NAME;

	@Override
	public String toString() {
		String uri = "http://localhost:8080/department";
		switch (this) {
		case CREATE_DEPARTMENT:
			return uri + "/create-department";
		case UPDATE_DEPARTMENT:
			return uri + "/update-department";
		case DELETE_DEPARTMENT:
			return uri + "/delete-department";
		case GET_DEPARTMENTS:
			return uri + "/get-departments";
		case ADD_EMPLOYEE:
			return uri + "/add-employee";
		case REMOVE_EMPLOYEE:
			return uri + "/remove-employee";
		case GET_DEPARTMENT:
			return uri + "/get-department";
		case GET_DEPARTMENT_NAME:
			return uri + "/get-department-by-name";
		default:
			return super.toString();
		}
	}

}
