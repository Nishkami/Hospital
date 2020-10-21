package com.org.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.demo.exception.ResourceNotFoundException;
import com.org.demo.model.Dependent;
import com.org.demo.model.PatientData;
import com.org.demo.repo.DependentRepo;
import com.org.demo.repo.MainRepo;

@RestController
@RequestMapping(path="/patient")
public class MainController {
	
	@Autowired
	MainRepo mainRepo;
	
	@Autowired
	DependentRepo depRepo;

	@GetMapping("/getAllData")
    public List<PatientData> getAllNotes() {
        return mainRepo.findAll();
    }
	
	@PostMapping("/addPatient")
	public PatientData postPData(@RequestBody PatientData data) {
		 return mainRepo.save(data);
	}
	
	@GetMapping("/getPatientById/{id}")
    public PatientData getPatientById(@PathVariable(value = "id") Long patientId) {
        return mainRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
    }

    @PutMapping("/updatePatient/{id}")
    public PatientData updatePatient(@PathVariable(value = "id") Long patientId,
                                            @RequestBody PatientData patientDetails) {

    	PatientData pData = mainRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));

    	pData.setFirstName(patientDetails.getFirstName());
    	pData.setLastName(patientDetails.getLastName());
    	pData.setDateOfBirth(patientDetails.getDateOfBirth());
    	pData.setPhoneNumber(patientDetails.getPhoneNumber());
    	pData.setStatus(patientDetails.isStatus());
    	pData.setDependents(patientDetails.getDependents());

    	PatientData updatedData = mainRepo.save(pData);
        return updatedData;
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long pId) {
    	PatientData pData = mainRepo.findById(pId)
                .orElseThrow(() -> new ResourceNotFoundException("PatientData", "id", pId));

    	Set<Dependent> dData= pData.getDependents();
    	
    	if(dData.size() > 0) {
    		dData.forEach(it -> {
    			depRepo.delete(it);
    		});
    	}
    	
    	mainRepo.delete(pData);

        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/updateDepndent/{id}")
    public Dependent updateDepndent(@PathVariable(value = "id") Long dId,
                                            @RequestBody Dependent dependentDetails) {

    	Dependent dData = depRepo.findById(dId)
                .orElseThrow(() -> new ResourceNotFoundException("Dependent", "id", dId));

    	dData.setFirstName(dependentDetails.getFirstName());
    	dData.setLastName(dependentDetails.getLastName());
    	dData.setDateOfBirth(dependentDetails.getDateOfBirth());
    	dData.setId(dependentDetails.getId());

    	Dependent updatedData = depRepo.save(dData);
        return updatedData;
    }
    
    @DeleteMapping("/deleteDependent/{id}")
    public ResponseEntity<?> deleteDependent(@PathVariable(value = "id") Long dId) {
    	Dependent dData = depRepo.findById(dId)
                .orElseThrow(() -> new ResourceNotFoundException("Dependent", "id", dId));

    	depRepo.delete(dData);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getDependentById/{id}")
    public Dependent getDependentById(@PathVariable(value = "id") Long dependentId) {
        return depRepo.findById(dependentId)
                .orElseThrow(() -> new ResourceNotFoundException("Dependent", "id", dependentId));
    }
    
    @PostMapping("/addDependent/{id}")
	public Dependent posDData(@RequestBody Dependent data, @PathVariable(value = "id") Long patientId) {
		
		PatientData pData = mainRepo.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
		
		data.setPatientData(pData);
		
		return depRepo.save(data);
	}
	
}
