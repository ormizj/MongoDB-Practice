package com.jbc.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.sequence.EmployeeSequence;

public interface EmployeeSequenceRepository extends MongoRepository<EmployeeSequence, Integer> {

}
