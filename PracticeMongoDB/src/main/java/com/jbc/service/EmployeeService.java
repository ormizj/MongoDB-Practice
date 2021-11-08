package com.jbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jbc.exception.model.ModelAlreadyExistsException;
import com.jbc.exception.model.ModelInvalidException;
import com.jbc.exception.model.ModelNotFoundException;
import com.jbc.exception.model.ModelNullException;
import com.jbc.exception.model.ModelEmptyException;
import com.jbc.model.Department;
import com.jbc.model.Employee;
import com.jbc.model.sequence.EmployeeSequence;
import com.jbc.repository.EmployeeRepository;
import com.jbc.repository.sequence.EmployeeSequenceRepository;
import com.jbc.service.ifc.EmployeeIfc;
import com.jbc.util.controller.url.DepartmentUrlUtil;
import com.jbc.util.model.ModelAttributeUtil;
import com.jbc.util.model.ModelTypeUtil;
import com.jbc.util.model.validation.EmployeeValidations;

@Service
public class EmployeeService implements EmployeeIfc, InitializingBean {

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EmployeeSequenceRepository employeeSequenceRepo;
	private long employeeSequence; /* value will come from the data-base */

	@Override
	public Employee createEmployee(Employee employee)
			throws ModelAlreadyExistsException, ModelInvalidException, ModelNullException {
		checkEmployee(employee);
		try {
			getEmployee(employee.getId());/* creating employee if employee not found */
			throw new ModelAlreadyExistsException(ModelTypeUtil.EMPLOYEE, ModelAttributeUtil.ID, employee.getId());
		} catch (ModelNotFoundException e) {
			employee.setId(employeeSequence);
			employeeSequence++;
			mongoTemplate.save(new EmployeeSequence(employeeSequence));
			return mongoTemplate.insert(employee);
		}
	}

	@Override
	public Employee updateEmployee(long employeeId, Employee employee)
			throws ModelNotFoundException, ModelInvalidException, ModelNullException {
		checkEmployee(employee);
		employee.setDepartmentName( /* keeping the employee's department */
				getEmployee(employeeId).getDepartmentName()); /* throwing exception if employee not found */
		return mongoTemplate.save(employee);
	}

	@Override
	public Employee updateEmployeeDepartment(long employeeId, String employeeDepartment)
			throws ModelNotFoundException, Exception {
		Employee employee = getEmployee(employeeId);
		employee.setDepartmentName(employeeDepartment);
		if (!employeeDepartment.isEmpty()) { /* nothing to add if employee has no department */
			Department department = getDepartmentRest(employeeDepartment);
			/* adding the employee to the department if needed */
			if (!department.getEmployeesIds().contains(employeeId))
				addEmployeeRest(department.getId(), employeeId);
		}
		return mongoTemplate.save(employee);
	}

	@Override
	public void deleteEmployee(long employeeId) throws ModelNotFoundException, Exception {
		Employee employee = getEmployee(employeeId);/* throwing exception if employee not found */
		if (!employee.getDepartmentName().isEmpty()) {
			Department department = getDepartmentRest(employee.getDepartmentName());
			removeEmployeeRest(department.getId(), employeeId);
		}
		employeeRepo.deleteById(employeeId);
	}

	@Override
	public List<Employee> getEmployees() throws ModelEmptyException {
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty())
			throw new ModelEmptyException(ModelTypeUtil.EMPLOYEE);
		return employees;
	}

	@Override
	public Employee getEmployee(long employeeId) throws ModelNotFoundException {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		if (!employee.isPresent())
			throw new ModelNotFoundException(ModelTypeUtil.EMPLOYEE, ModelAttributeUtil.ID, employeeId);
		return employee.get();
	}

	private void checkEmployee(Employee employee) throws ModelInvalidException, ModelNullException {
		if (employee == null || employee.getFirstName() == null || employee.getLastName() == null
				|| employee.getDepartmentName() == null || employee.getProfession() == null) {
			throw new ModelNullException(ModelTypeUtil.EMPLOYEE);
		}
		if (employee.getFirstName().length() < EmployeeValidations.FIRST_NAME_MIN.toInt())
			throw new ModelInvalidException(ModelTypeUtil.EMPLOYEE, ModelAttributeUtil.FIRST_NAME);
		if (employee.getLastName().length() < EmployeeValidations.LAST_NAME_MIN.toInt())
			throw new ModelInvalidException(ModelTypeUtil.EMPLOYEE, ModelAttributeUtil.LAST_NAME);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (employeeSequenceRepo.findAll().isEmpty())
			employeeSequence = mongoTemplate.save(new EmployeeSequence(getEmployeesNextId())).getSequence();
		else
			employeeSequence = employeeSequenceRepo.findAll().get(0).getSequence();
	}

	private long getEmployeesNextId() {
		long maxId = 0;
		for (Employee employee : employeeRepo.findAll()) {
			if (employee.getId() > maxId)
				maxId = employee.getId();
		}
		return maxId + 1;
	}

	/* communcating to the Department through Rest to mock Microservice structure */
	private Department getDepartmentRest(String departmentName) {
		return new RestTemplate()
				.getForEntity(DepartmentUrlUtil.GET_DEPARTMENT_NAME + "?name=" + departmentName, Department.class)
				.getBody();
	}

	private void addEmployeeRest(long departmentId, long employeeId) {
		new RestTemplate().put(
				DepartmentUrlUtil.ADD_EMPLOYEE + "?departmentId=" + departmentId + "&employeeId=" + employeeId, null);
	}

	private void removeEmployeeRest(long departmentId, long employeeId) {
		new RestTemplate().put(
				DepartmentUrlUtil.REMOVE_EMPLOYEE + "?departmentId=" + departmentId + "&employeeId=" + employeeId,
				null);
	}

}
