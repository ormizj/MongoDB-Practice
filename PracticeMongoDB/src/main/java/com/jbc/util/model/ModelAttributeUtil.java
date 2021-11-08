package com.jbc.util.model;

/*enum that contains all the Model attributes*/
public enum ModelAttributeUtil {

	/* shared attributes */
	ID,
	/* department attributes */
	NAME, CAPACITY, EMPLOYEES_IDS,
	/* employee attributes */
	FIRST_NAME, LAST_NAME, PROFESSION, DEPARTMENT_NAME;

	@Override
	public String toString() {
		switch (this) {
		case ID:
			return "ID";
		case NAME:
			return "Name";
		case CAPACITY:
			return "Capacity";
		case EMPLOYEES_IDS:
			return "Employees ID's";
		case FIRST_NAME:
			return "First Name";
		case LAST_NAME:
			return "Last Name";
		case PROFESSION:
			return "Profession";
		case DEPARTMENT_NAME:
			return "Department Name";
		default:
			return super.toString();
		}
	}

}
