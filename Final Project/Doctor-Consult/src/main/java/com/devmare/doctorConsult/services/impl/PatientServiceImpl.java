package com.devmare.doctorConsult.services.impl;

import com.devmare.doctorConsult.entity.LoginRequest;
import com.devmare.doctorConsult.entity.Patient;
import com.devmare.doctorConsult.repository.PatientRepository;
import com.devmare.doctorConsult.services.PatientService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient registerPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()) != null) {
            throw new RuntimeException("Doctor is alreay exists!");
        }
        return patientRepository.save(patient);
    }

    @Override
    public boolean loginPatient(LoginRequest loginRequest) {
        Patient currentPatient = patientRepository.findByEmail(loginRequest.getEmail());
        return currentPatient != null && loginRequest.getPassword().matches(currentPatient.getPassword());
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
