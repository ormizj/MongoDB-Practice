package com.jbc.controller.api;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.request.DepartmentRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Department;
import com.jbc.model.Employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface DepartmentApi {
	
	
	@ApiOperation(value = "Create Department", notes = "Creates a Department and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Department.class),
			@ApiResponse(code = 400, message = "Department creation failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/create-department", produces = "application/json")
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequest department);
	
	
	@ApiOperation(value = "Update Department", notes = "Updates a Department and returns it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Department.class),
			@ApiResponse(code = 400, message = "Department update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-department", produces = "application/json")
	public ResponseEntity<?> updateDepartment(@RequestBody DepartmentRequest department);
	
	
	@ApiOperation(value = "Delete Department", notes = "Deletes a Department")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Department deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/delete-department", produces = "application/json")
	public ResponseEntity<?> deleteDepartment(@ApiParam(required = true, value = "ID of the Department to delete", example = "0") @RequestParam long departmentId);
	
	
	@ApiOperation(value = "Get Departments", notes = "Returns a list of all the Departments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Department.class),
			@ApiResponse(code = 400, message = "Departments not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-departments", produces = "application/json")
	public ResponseEntity<?> getDepartments();
	
	
	@ApiOperation(value = "Add Employee", notes = "Adds an Employee to a Department and returns the added Employee\n\n[Removes the added Employee from his other Department, if he has one]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employee additon failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/add-employee", produces = "application/json")
	public ResponseEntity<?> AddEmployee(@ApiParam(required = true, value = "ID of the Department to add the Employee to", example = "0") @RequestParam long departmentId,@ApiParam(required = true, value = "ID of the Employee to add", example = "0") @RequestParam long employeeId);
	
	@ApiOperation(value = "Remove Employee", notes = "Removes an Employee from a Department")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Employee remove failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/remove-employee", produces = "application/json")
	public ResponseEntity<?> removeEmployee(
			@ApiParam(required = true, value = "ID of the Department to remove the Employee from", example = "0") @RequestParam long departmentId,
			@ApiParam(required = true, value = "ID of the Employee to remove",example = "0") @RequestParam long employeeId);
	
	@ApiOperation(value = "Get Department", notes = "Returns Department by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Department.class),
			@ApiResponse(code = 400, message = "Department not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-department", produces = "application/json")
	public ResponseEntity<?> getDepartment(@ApiParam(required = true, value = "ID of the Department to return", example = "0") @RequestParam long departmentId);
	
	
	@ApiOperation(value = "Get Department", notes = "Returns Department by name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Department.class),
			@ApiResponse(code = 400, message = "Department not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-department-by-name", produces = "application/json")
	public ResponseEntity<?> getDepartment(@ApiParam(required = true, value = "Name of the Department to return") @RequestParam String name);
	
	
	
	
}
