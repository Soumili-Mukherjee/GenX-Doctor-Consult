package com.devmare.doctorConsult.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String password;
    private Integer age;
    private String gender;
}
