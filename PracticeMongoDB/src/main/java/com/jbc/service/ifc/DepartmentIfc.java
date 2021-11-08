package com.jbc.service.ifc;

import java.util.List;

import com.jbc.exception.model.ModelInvalidException;
import com.jbc.exception.model.ModelNotFoundException;
import com.jbc.exception.model.ModelNullException;
import com.jbc.exception.model.ModelAlreadyExistsException;
import com.jbc.exception.model.ModelEmptyException;
import com.jbc.model.Department;
import com.jbc.model.Employee;

public interface DepartmentIfc {

	public Department createDepartment(Department department)
			throws ModelAlreadyExistsException, ModelNullException, ModelInvalidException;

	public Department updateDepartment(long departmentId, Department department) throws ModelNotFoundException,
			ModelNullException, ModelInvalidException, ModelAlreadyExistsException, Exception;

	public void deleteDepartment(long departmentId) throws ModelNotFoundException, Exception;

	public List<Department> getDepartments() throws ModelEmptyException;

	public Employee addEmployee(long departmentId, long employeeId)
			throws ModelNotFoundException, ModelNotFoundException, ModelNullException, ModelInvalidException,
			ModelAlreadyExistsException, ModelInvalidException, ModelNullException, Exception;

	public void removeEmployee(long departmentId, long employeeId) throws ModelNotFoundException, Exception;

	public Department getDepartment(long departmentId) throws ModelNotFoundException;

	public Department getDepartment(String name) throws ModelNotFoundException;

}
