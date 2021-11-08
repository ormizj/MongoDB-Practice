package com.jbc.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(value = "departments", collation = "{'locale':'en', 'strength':2}")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ApiModel(description = "Name between Departments must be unique")
public class Department {

	@Id
	@EqualsAndHashCode.Include
	@ApiModelProperty(value = "Auto-incremented", example = "0")
	private long id;

	@NotNull
	@Indexed(unique = true)
	@Size(min = 2)
	private String name;

	@Min(6)
	@ApiModelProperty(example = "6")
	private int capacity;

	@ApiModelProperty(value = "Only as response")
	private Set<Long> employeesIds;

	public Department(String name, int capacity) {
		employeesIds= new HashSet<>();
		this.name = name;
		this.capacity = capacity;
	}

	public Department(long id, String name, int capacity) {
		employeesIds= new HashSet<>();
		this.id=id;
		this.name = name;
		this.capacity = capacity;
	}
	
}
