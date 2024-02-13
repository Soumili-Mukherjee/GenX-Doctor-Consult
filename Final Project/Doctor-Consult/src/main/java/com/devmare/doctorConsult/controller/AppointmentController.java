package com.devmare.doctorConsult.controller;

import com.devmare.doctorConsult.entity.Appointment;
import com.devmare.doctorConsult.services.AppointmentService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    //! http://localhost:8081/api/v1/appointment/doctors/{doctorName}
    @CrossOrigin
    @PostMapping("doctors/{doctorName}")
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody Appointment appointment,
            @PathVariable String doctorName
    ) {
        return new ResponseEntity<>(appointmentService.createAppointment(appointment, doctorName), HttpStatus.OK);
    }

    //! http://localhost:8081/api/v1/appointment/doctors/{doctorName}
    @GetMapping("doctors/{doctorName}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctorName(
            @PathVariable String doctorName
    ) {
        return new ResponseEntity<>(appointmentService.getAppointmentByDoctor(doctorName), HttpStatus.FOUND);
    }

    //! http://localhost:8081/api/v1/appointment
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments() {
        return new ResponseEntity<>(appointmentService.getAppointments(), HttpStatus.FOUND);
    }

    //! http://localhost:8081/api/v1/appointment/{id}
    @PutMapping("{id}")
    public ResponseEntity<String> confirmAppointment(
            @PathVariable Integer id
    ) {
        boolean isConfirm = appointmentService.confirmAppointment(id);
        if (isConfirm) {
            return ResponseEntity.ok("Appointment is confirmed!");
        } else {
            return new ResponseEntity<>("Appointment isnot confirmed yet!", HttpStatus.BAD_REQUEST);
        }
    }
}
