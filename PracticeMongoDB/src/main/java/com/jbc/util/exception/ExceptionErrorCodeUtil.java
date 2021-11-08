package com.jbc.util.exception;

/*enum that contains all the Exceptions error codes, values are not all upper-case for convenience*/
public enum ExceptionErrorCodeUtil {

	DepartmentTemplateException("TMP-000.001"), EmployeeTemplateException("TMP-000.002"),

	EmployeeAlreadyExistsException("EMP-001.001"), EmployeeNotFoundException("EMP-001.002"),
	EmployeesIsEmptyException("EMP-001.003"), EmployeeNullException("EMP-001.004"),
	EmployeeInvalidException("EMP-001.005"),

	DepartmentNullException("DPR-002.001"), DepartmentInvalidException("DPR-002.002"),
	DepartmentAlreadyExistsException("DPR-002.003"), DepartmentNotFoundException("DPR-002.004"),
	DepartmentsIsEmptyException("DPR-002.005");

	private final String ERROR_CODE;

	private ExceptionErrorCodeUtil(String errorCode) {
		ERROR_CODE = errorCode;
	}

	@Override
	public String toString() {
		return ERROR_CODE;
	}

}
