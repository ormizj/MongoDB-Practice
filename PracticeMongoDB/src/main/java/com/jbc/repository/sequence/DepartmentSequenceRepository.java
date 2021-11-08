package com.jbc.repository.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.sequence.DepartmentSequence;

public interface DepartmentSequenceRepository extends MongoRepository<DepartmentSequence, Integer> {

}
