package com.jbc.util.model.validation;

/*enum that contains all the Department required validations*/
public enum DepartmentValidations {

	NAME_MIN(2), CAPACITY_MIN(6);

	private final int NUMBER;

	private DepartmentValidations(int number) {
		NUMBER = number;
	}

	public int toInt() {
		return NUMBER;
	}

}
