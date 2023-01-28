package com.javaprojects.dao;

import org.springframework.data.repository.CrudRepository;

import com.javaprojects.entity.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
	
	public Hospital findById(int id); 

}
