package com.devmare.doctorConsult.services.impl;

import com.devmare.doctorConsult.entity.Appointment;
import com.devmare.doctorConsult.entity.Doctor;
import com.devmare.doctorConsult.exception.ResourceNotFoundException;
import com.devmare.doctorConsult.repository.AppointmentRepository;
import com.devmare.doctorConsult.repository.DoctorRepository;
import com.devmare.doctorConsult.services.AppointmentService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Appointment createAppointment(Appointment appointment, String doctorName) {
        List<Doctor> doctors = doctorRepository.findByName(doctorName);
        if (!doctors.isEmpty()) {
            appointment.setDoctor(doctors.get(0));
            appointment.setDoctorName(doctorName);
            appointment.setConfirmed(false);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Doctor not found with name: " + doctorName);
        }
    }

    @Override
    public List<Appointment> getAppointmentByDoctor(String doctorName) {
        return appointmentRepository.findByDoctorName(doctorName);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public boolean confirmAppointment(Integer id) {
        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Appointment is not found with id " + id));
        appointment.setConfirmed(true);
        appointmentRepository.save(appointment);
        return true;
    }
}
