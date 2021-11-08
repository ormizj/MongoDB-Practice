package com.jbc.message.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import com.jbc.model.Department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

/*class to limit the sent information through the Front-End related to a Department*/
@Getter
@Setter
@ApiModel(description = "Name between Departments must be unique")
public class DepartmentRequest {

	
	@ApiModelProperty(value = "Auto-incremented", example = "0")
	private long id;

	@NotNull
	@Size(min = 2)
	private String name;

	@Min(6)
	@ApiModelProperty(example = "6")
	private int capacity;
	
	public Department toDepartment() {
		return new Department(id, name, capacity);
	}
}
