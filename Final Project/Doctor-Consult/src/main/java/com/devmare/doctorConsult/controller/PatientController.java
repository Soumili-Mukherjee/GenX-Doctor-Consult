package com.devmare.doctorConsult.controller;

import com.devmare.doctorConsult.entity.LoginRequest;
import com.devmare.doctorConsult.entity.Patient;
import com.devmare.doctorConsult.services.PatientService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    //! http://localhost:8081/api/v1/patient/register
    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<Patient> registerPatient(
            @RequestBody Patient patient
    ) {
        Patient registeredPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/v1/patient/login
    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<Object> loginPatient(
            @RequestBody LoginRequest loginRequest
    ) {
        boolean isLogin = patientService.loginPatient(loginRequest);

        if (isLogin) {
            return ResponseEntity.ok().body(Map.of("message", "Login successful"));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    //! http://localhost:8081/api/v1/patient
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.FOUND);
    }
}
