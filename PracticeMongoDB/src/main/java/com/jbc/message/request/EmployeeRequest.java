package com.jbc.message.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jbc.model.Employee;
import com.jbc.util.model.EmployeeProfessionUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/*class to limit the sent information through the Front-End related to a Employee*/
@Getter
@Setter
public class EmployeeRequest {

	@ApiModelProperty(value = "Auto-incremented", example = "0")
	private long id;

	@NotNull
	@Size(min = 2)
	private String firstName;

	@NotNull
	@Size(min = 2)
	private String lastName;

	@NotNull
	private EmployeeProfessionUtil profession;

	public Employee toEmployee() {
		return new Employee(id, firstName, lastName, profession);
	}

}
