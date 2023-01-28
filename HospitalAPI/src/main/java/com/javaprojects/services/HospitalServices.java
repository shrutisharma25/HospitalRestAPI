package com.javaprojects.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaprojects.dao.HospitalRepository;
import com.javaprojects.entity.Hospital;

@Component
public class HospitalServices {
	
	@Autowired
	HospitalRepository hospitalRepository;

	//GET ALL HOSPITAL
	public List<Hospital> getAllHospital(){
		List<Hospital> list = (List<Hospital>)this.hospitalRepository.findAll();
		return list;
	}

	//ADD THE HOSPITAL DETAIL
	public Hospital addHospital(Hospital h) {
		Hospital hospital = hospitalRepository.save(h);
		return hospital;
	}

	//GET HOSPITAL DETAILS OF PARTICULAR ID
	public Hospital getHospitalById(int id){
		Hospital hospital =null;
		try {
			this.hospitalRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospital;
	}

	//FOR DELEING HOSPITAL DETAILS
	public void deleteHospital(int id) {
		hospitalRepository.deleteById(id);
	}

	//FOR UPDATING THE HOSPITAL DETAIL
	public void updateHospital(Hospital hospital, int hospitalId) {
		hospital.setHId(hospitalId);
		hospitalRepository.save(hospital);
	}

}
