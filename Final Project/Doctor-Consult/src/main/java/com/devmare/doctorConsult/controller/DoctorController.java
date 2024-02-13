package com.devmare.doctorConsult.controller;

import com.devmare.doctorConsult.entity.Doctor;
import com.devmare.doctorConsult.entity.LoginRequest;
import com.devmare.doctorConsult.services.DoctorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    //! http://localhost:8081/api/v1/doctor/register
    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<Doctor> registerDoctor(
            @RequestBody Doctor doctor
    ) {
        Doctor registeredDoctor = doctorService.registerDoctor(doctor);
        return new ResponseEntity<>(registeredDoctor, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/v1/doctor/login
    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<Object> loginDoctor(
            @RequestBody LoginRequest loginRequest
    ) {
        boolean isLogin = doctorService.loginDoctor(loginRequest);

        if (isLogin) {
            return ResponseEntity.ok().body(Map.of("message", "Login successful"));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    //! http://localhost:8081/api/v1/doctor/4
    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Integer id,
            @RequestBody Doctor doctor
    ) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    //! http://localhost:8081/api/v1/doctor
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.allDoctorList(), HttpStatus.FOUND);
    }

    //! http://localhost:8081/api/v1/doctor/location/{location}
    @CrossOrigin
    @GetMapping("location/{location}")
    public ResponseEntity<List<Doctor>> findDoctorsByLocation(
            @PathVariable String location
    ) {
        return new ResponseEntity<>(doctorService.findDoctorByLocation(location), HttpStatus.FOUND);
    }

    //! http://localhost:8081/api/v1/doctor/specializaton/{specializaton}
    @CrossOrigin
    @GetMapping("specializaton/{specializaton}")
    public ResponseEntity<List<Doctor>> findDoctorsBySpecializaton(
            @PathVariable String specializaton
    ) {
        return new ResponseEntity<>(doctorService.findDoctorBySpecialization(specializaton), HttpStatus.FOUND);
    }

    //! http://localhost:8081/api/v1/doctor/name/{name}
    @CrossOrigin
    @GetMapping("name/{name}")
    public ResponseEntity<List<Doctor>> findDoctorsByName(
            @PathVariable String name
    ) {
        return new ResponseEntity<>(doctorService.findDoctorByName(name), HttpStatus.FOUND);
    }
}
