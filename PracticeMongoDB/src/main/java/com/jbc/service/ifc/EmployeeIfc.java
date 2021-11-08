package com.jbc.service.ifc;

import java.util.List;

import com.jbc.exception.model.ModelAlreadyExistsException;
import com.jbc.exception.model.ModelInvalidException;
import com.jbc.exception.model.ModelNotFoundException;
import com.jbc.exception.model.ModelNullException;
import com.jbc.exception.model.ModelEmptyException;
import com.jbc.model.Employee;

public interface EmployeeIfc {

	public Employee createEmployee(Employee employee)
			throws ModelEmptyException, ModelInvalidException, ModelAlreadyExistsException, ModelNullException;

	public Employee updateEmployee(long employeeId, Employee employee)
			throws ModelNotFoundException, ModelInvalidException, ModelNullException;

	public Employee updateEmployeeDepartment(long employeeId, String employeeDepartment)
			throws ModelNotFoundException, Exception;

	public void deleteEmployee(long employeeId) throws ModelNotFoundException, Exception;

	public List<Employee> getEmployees() throws ModelEmptyException;

	public Employee getEmployee(long employeeId) throws ModelNotFoundException;

}
