package com.javaprojects.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaprojects.entity.Hospital;
import com.javaprojects.services.HospitalServices;

@Controller
public class HospitalController {
	
	@Autowired
	private HospitalServices hospitalServices;

	@GetMapping("/hospital")
	public ResponseEntity<List<Hospital>> getHospital() {
		List<Hospital> list = this.hospitalServices.getAllHospital();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@PostMapping("/hospital")
	public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
		Hospital h=null;
		try {
			h=this.hospitalServices.addHospital(hospital);
			return ResponseEntity.of(Optional.of(h));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/hospital/{id}")
	public ResponseEntity<Hospital> getHospital(@PathVariable("id") int id) {
		Hospital hospital = hospitalServices.getHospitalById(id);
		if(hospital==null) { 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(hospital));
	}
	
	@DeleteMapping("/hospital/{id}")
	public ResponseEntity<Void> deleteHospital(@PathVariable("id") int id) {
		try {
		    this.hospitalServices.deleteHospital(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/hospital/{hospitalId}")
	public Hospital updateHospital(@RequestBody Hospital hospital, @PathVariable("hospitalId") int hospitalId) {
		this.hospitalServices.updateHospital(hospital,hospitalId);
		return hospital;		
	}


}
