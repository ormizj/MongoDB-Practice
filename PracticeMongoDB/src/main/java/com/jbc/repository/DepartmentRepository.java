package com.jbc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.Department;

public interface DepartmentRepository extends MongoRepository<Department, Long> {

	public Optional<Department> findByIdNotAndNameIgnoreCase(long departmentId, String departmentName);

	public Optional<Department> findByNameIgnoreCase(String name);

}
