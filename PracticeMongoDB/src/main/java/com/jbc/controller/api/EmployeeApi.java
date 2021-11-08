package com.jbc.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbc.message.request.EmployeeRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.model.Employee;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/*Swagger documentation for Employee*/
public interface EmployeeApi {

	@ApiOperation(value = "Create Employee", notes = "Creates an Employee and returns it\n\n[id - unnecessary]")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employee creation failed", response = ExceptionResponse.class) })
	@PostMapping(value = "/create-employee", produces = "application/json")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employee);

	@ApiOperation(value = "Update Employee", notes = "Updates an Employee and returns it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employee update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-employee", produces = "application/json")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employee);

	@ApiOperation(value = "Update Employee Department", notes = "Updates an Employee Department returns the Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employee Department update failed", response = ExceptionResponse.class) })
	@PutMapping(value = "/update-employee-department", produces = "application/json")
	public ResponseEntity<?> updateEmployeeDepartment(
			@ApiParam(required = true, value = "ID of the Employee to update", example = "0") @RequestParam long employeeId,
			@ApiParam(required = true, value = "Name of the Employee new Department") @RequestParam String departmentName);

	@ApiOperation(value = "Delete Employee", notes = "Deletes an Employee")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful operation", response = SuccessResponse.class),
			@ApiResponse(code = 400, message = "Employee deletion failed", response = ExceptionResponse.class) })
	@DeleteMapping(value = "/delete-employee", produces = "application/json")
	public ResponseEntity<?> deleteEmployee(
			@ApiParam(required = true, value = "ID of the Employee to delete", example = "0") @RequestParam long employeeId);

	@ApiOperation(value = "Get Employees", notes = "Returns a list of all the Employees")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employees not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-employees", produces = "application/json")
	public ResponseEntity<?> getEmployees();

	@ApiOperation(value = "Get Employee", notes = "Returns Employee by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Employee.class),
			@ApiResponse(code = 400, message = "Employee not found", response = ExceptionResponse.class) })
	@GetMapping(value = "/get-employee", produces = "application/json")
	public ResponseEntity<?> getEmployee(
			@ApiParam(required = true, value = "ID of the Employee to return", example = "0") @RequestParam long employeeId);
}
