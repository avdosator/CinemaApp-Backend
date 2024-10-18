package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private String city; // will be relationship with City entity

    @Column(name = "role")
    private String role;

    @Column(name = "created_at")
    private Date dateCreated;

    @Column(name = "updated_at")
    private Date updatedAt;
}
