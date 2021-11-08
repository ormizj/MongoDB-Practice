package com.jbc.util.model.validation;

/*enum that contains all the Employee required validtaions*/
public enum EmployeeValidations {

	FIRST_NAME_MIN(2), LAST_NAME_MIN(2);

	private final int NUMBER;

	private EmployeeValidations(int number) {
		NUMBER = number;
	}

	public int toInt() {
		return NUMBER;
	}

}
