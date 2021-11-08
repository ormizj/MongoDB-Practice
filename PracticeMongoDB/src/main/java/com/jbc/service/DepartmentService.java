package com.jbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jbc.exception.model.ModelInvalidException;
import com.jbc.exception.model.ModelNotFoundException;
import com.jbc.exception.model.ModelNullException;
import com.jbc.exception.model.ModelAlreadyExistsException;
import com.jbc.exception.model.ModelEmptyException;
import com.jbc.model.Department;
import com.jbc.model.Employee;
import com.jbc.model.sequence.DepartmentSequence;
import com.jbc.repository.DepartmentRepository;
import com.jbc.repository.sequence.DepartmentSequenceRepository;
import com.jbc.service.ifc.DepartmentIfc;
import com.jbc.util.controller.url.EmployeeUrlUtil;
import com.jbc.util.model.ModelAttributeUtil;
import com.jbc.util.model.ModelTypeUtil;
import com.jbc.util.model.validation.DepartmentValidations;

@Service
public class DepartmentService implements DepartmentIfc, InitializingBean {

	@Autowired
	private DepartmentRepository departmentRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DepartmentSequenceRepository departmentSequenceRepo;
	private long departmentSequence; /* value will come from the data-base */

	@Override
	public Department createDepartment(Department department)
			throws ModelAlreadyExistsException, ModelNullException, ModelInvalidException {
		checkDepartment(department);
		try {
			getDepartment(department.getId());/* creating department if department not found */
			throw new ModelAlreadyExistsException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.ID, department.getId());
		} catch (ModelNotFoundException e) {
			department.setId(departmentSequence);
			departmentSequence++;
			mongoTemplate.save(new DepartmentSequence(departmentSequence));
			return mongoTemplate.insert(department);
		}
	}

	@Override
	public Department updateDepartment(long departmentId, Department department) throws ModelNotFoundException,
			ModelNullException, ModelInvalidException, ModelAlreadyExistsException, Exception {
		checkDepartment(department);
		department.setEmployeesIds(/* keeping the department's employees */
				getDepartment(departmentId).getEmployeesIds());/* throwing exception if department not found */
		department = mongoTemplate.save(department);
		/* updating the employees to match the department name */
		for (Long employeeId : department.getEmployeesIds()) {
			updateEmployeeDepartmentRest(employeeId, department.getName());
		}
		return department;
	}

	@Override
	public void deleteDepartment(long departmentId) throws ModelNotFoundException, Exception {
		Department deletedDepartment = getDepartment(departmentId);/* throwing exception if department not found */
		for (long employeeId : deletedDepartment.getEmployeesIds()) {
			updateEmployeeDepartmentRest(employeeId, "");
		}
		departmentRepo.deleteById(departmentId);
	}

	@Override
	public List<Department> getDepartments() throws ModelEmptyException {
		List<Department> departments = departmentRepo.findAll();
		if (departments.isEmpty())
			throw new ModelEmptyException(ModelTypeUtil.DEPARTMENT);
		return departments;
	}

	@Override
	public Department getDepartment(long departmentId) throws ModelNotFoundException {
		Optional<Department> department = departmentRepo.findById(departmentId);
		if (!department.isPresent())
			throw new ModelNotFoundException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.ID, departmentId);
		return department.get();
	}

	@Override
	public Department getDepartment(String name) throws ModelNotFoundException {
		Optional<Department> department = departmentRepo.findByNameIgnoreCase(name);
		if (!department.isPresent()) {
			throw new ModelNotFoundException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.NAME, name);
		}
		return department.get();
	}

	@Override
	public Employee addEmployee(long departmentId, long employeeId)
			throws ModelNotFoundException, ModelNotFoundException, ModelNullException, ModelInvalidException,
			ModelAlreadyExistsException, ModelInvalidException, ModelNullException, Exception {
		Employee employee = getEmployeeRest(employeeId);
		Department department = getDepartment(departmentId);
		/* removing department if the employee is in a different department */
		if (!employee.getDepartmentName().isEmpty()) {
			try {
				Department currentEmployeeDepartment = getDepartment(employee.getDepartmentName());
				currentEmployeeDepartment.getEmployeesIds().remove(employeeId);
				mongoTemplate.save(currentEmployeeDepartment);
			} catch (ModelNotFoundException e) {
				/* nothing to remove if department is not found */
			}
		}
		/* adding employee to department */
		department.getEmployeesIds().add(employeeId);
		mongoTemplate.save(department);
		/* adding department to employee if needed */
		if (!employee.getDepartmentName().equalsIgnoreCase(department.getName())) {
			employee.setDepartmentName(department.getName());
			updateEmployeeDepartmentRest(employeeId, department.getName());
		}
		return employee;
	}

	@Override
	public void removeEmployee(long departmentId, long employeeId) throws ModelNotFoundException, Exception {
		Department department = getDepartment(departmentId);
		department.getEmployeesIds().remove(employeeId);
		updateEmployeeDepartmentRest(employeeId, "");
		mongoTemplate.save(department);
	}

	/*ensures that the Department is valid*/
	private void checkDepartment(Department department)
			throws ModelNullException, ModelInvalidException, ModelAlreadyExistsException {
		if (department == null || department.getName() == null)
			throw new ModelNullException(ModelTypeUtil.DEPARTMENT);
		if (department.getName().length() < DepartmentValidations.NAME_MIN.toInt())
			throw new ModelInvalidException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.NAME);
		if (department.getCapacity() < DepartmentValidations.CAPACITY_MIN.toInt())
			throw new ModelInvalidException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.CAPACITY);
		if (departmentRepo.findByIdNotAndNameIgnoreCase(department.getId(), department.getName()).isPresent())
			throw new ModelAlreadyExistsException(ModelTypeUtil.DEPARTMENT, ModelAttributeUtil.NAME,
					department.getName());
	}

	/* setting up the departmentSequence for auto-incrementation */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (departmentSequenceRepo.findAll().isEmpty()) {
			departmentSequence = mongoTemplate.save(new DepartmentSequence(getDepartmentsNextId())).getSequence();
		} else
			departmentSequence = departmentSequenceRepo.findAll().get(0).getSequence();
	}

	/* returns the next available department ID */
	private long getDepartmentsNextId() {
		long maxId = 0;
		for (Department department : departmentRepo.findAll()) {
			if (department.getId() > maxId)
				maxId = department.getId();
		}
		return maxId + 1;
	}

	/* communcating to the Employee through Rest to mock Microservice structure */
	private Employee getEmployeeRest(long employeeId) {
		return new RestTemplate()
				.getForEntity(EmployeeUrlUtil.GET_EMPLOYEE + "?employeeId=" + employeeId, Employee.class).getBody();
	}

	private void updateEmployeeDepartmentRest(long employeeId, String departmentName) {
		new RestTemplate().put(EmployeeUrlUtil.UPDATE_EMPLOYEE_DEPARTMENT + "?departmentName=" + departmentName
				+ "&employeeId=" + employeeId, null);
	}

}
