package com.devmare.doctorConsult.services;

import com.devmare.doctorConsult.entity.LoginRequest;
import com.devmare.doctorConsult.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient registerPatient(Patient patient);

    boolean loginPatient(LoginRequest loginRequest);

    List<Patient> getAllPatients();
}
