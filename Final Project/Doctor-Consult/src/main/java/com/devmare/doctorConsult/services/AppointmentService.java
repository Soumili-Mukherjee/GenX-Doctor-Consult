package com.devmare.doctorConsult.services;

import com.devmare.doctorConsult.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment, String doctorName);

    List<Appointment> getAppointmentByDoctor(String doctorName);
    List<Appointment> getAppointments();

    boolean confirmAppointment(Integer id);
}
