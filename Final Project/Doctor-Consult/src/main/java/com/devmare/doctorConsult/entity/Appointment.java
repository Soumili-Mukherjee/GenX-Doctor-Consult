package com.devmare.doctorConsult.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String phone;

    private String email;

    private boolean isConfirmed;

    /*private Date date;

    private LocalDateTime localDateTime;*/

    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
