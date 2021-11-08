package com.jbc.util.model;

/*enum that contains all the Model types*/
public enum ModelTypeUtil {

	DEPARTMENT, EMPLOYEE;

	@Override
	public String toString() {
		switch (this) {
		case DEPARTMENT:
			return "Department";
		case EMPLOYEE:
			return "Employee";
		default:
			return super.toString();
		}
	}

	public String toPlural() {
		switch (this) {
		case DEPARTMENT:
			return "Departments";
		case EMPLOYEE:
			return "Employees";
		default:
			return super.toString();
		}
	}

}
