package com.jbc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}
