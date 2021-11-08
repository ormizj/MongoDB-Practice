package com.jbc.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Throws exceptions, for unsuccessful responses\n\n"
		+ "Exceptions List:\r\n\n"
		+ "RestTemplate Exceptions:\n"
		+ "DepartmentTemplateException - TMP-000.001\n"
		+ "EmployeeTemplateException - TMP-000.002\n"
		+ "\n"
		+ "Employee Exceptions:\n"
		+ "EmployeeAlreadyExistsException - EMP-001.001\n"
		+ "EmployeeNotFoundException - EMP-001.002\n"
		+ "EmployeesIsEmptyException - EMP-001.003\n"
		+ "EmployeeNullException - EMP-001.004\n"
		+ "EmployeeInvalidException - EMP-001.005\n"
		+ "\n"
		+ "Department Exceptions:\n"
		+ "DepartmentNullException - DPR-002.001\n"
		+ "DepartmentInvalidException - DPR-002.002\n"
		+ "DepartmentAlreadyExistsException - DPR-002.003\n"
		+ "DepartmentNotFoundException - DPR-002.004\n"
		+ "DepartmentsIsEmptyException - DPR-002.005")
public class ExceptionResponse {

	@ApiModelProperty(value = "Exception message")
	private String response;
	@ApiModelProperty(value = "Exception error code", example = "EMP-001.001")
	private String errorCode;
		
}
