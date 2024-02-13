package com.devmare.doctorConsult.services.impl;

import com.devmare.doctorConsult.entity.Doctor;
import com.devmare.doctorConsult.entity.LoginRequest;
import com.devmare.doctorConsult.exception.ResourceNotFoundException;
import com.devmare.doctorConsult.repository.DoctorRepository;
import com.devmare.doctorConsult.services.DoctorService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.findByEmail(doctor.getEmail()) != null) {
            throw new RuntimeException("Doctor is alreay exists!");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public boolean loginDoctor(LoginRequest loginRequest) {
        Doctor currentDoctor = doctorRepository.findByEmail(loginRequest.getEmail());
        return currentDoctor != null && loginRequest.getPassword().matches(currentDoctor.getPassword());
    }

    @Override
    public Doctor updateDoctor(Integer id, Doctor doctor) {
        Doctor currentDoctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor not found with id " + id)
        );
        currentDoctor.setName(doctor.getName());
        currentDoctor.setEmail(doctor.getEmail());
        currentDoctor.setPassword(doctor.getPassword());
        currentDoctor.setPassword(doctor.getPhone());
        currentDoctor.setSpecialization(doctor.getSpecialization());
        currentDoctor.setLocation(doctor.getLocation());
        currentDoctor.setExperience(doctor.getExperience());
        return doctorRepository.save(currentDoctor);
    }

    @Override
    public List<Doctor> allDoctorList() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findDoctorByLocation(String location) {
        return doctorRepository.findByLocation(location);
    }

    @Override
    public List<Doctor> findDoctorBySpecialization(String specializaton) {
        return doctorRepository.findBySpecialization(specializaton);
    }

    @Override
    public List<Doctor> findDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }
}
