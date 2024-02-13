package com.devmare.doctorConsult.services;

import com.devmare.doctorConsult.entity.Doctor;
import com.devmare.doctorConsult.entity.LoginRequest;

import java.util.List;

public interface DoctorService {

    Doctor registerDoctor(Doctor doctor);
    boolean loginDoctor(LoginRequest loginRequest);

    Doctor updateDoctor(Integer id, Doctor doctor);

    List<Doctor> allDoctorList();

    List<Doctor> findDoctorByLocation(String location);

    List<Doctor> findDoctorBySpecialization(String specializaton);

    List<Doctor> findDoctorByName(String name);
}
