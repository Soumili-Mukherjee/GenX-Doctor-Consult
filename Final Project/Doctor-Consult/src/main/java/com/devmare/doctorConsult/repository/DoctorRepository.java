package com.devmare.doctorConsult.repository;

import com.devmare.doctorConsult.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByEmail(String email);

    List<Doctor> findByLocation(String location);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByName(String name);
}
