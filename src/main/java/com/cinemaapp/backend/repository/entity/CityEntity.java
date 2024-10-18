package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "city")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CityEntity {

    @Id
    @Column(name = "city_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private int postalCode;

    @OneToMany(mappedBy = "city")
    private List<UserEntity> userEntities;

    @Column(name = "created_at")
    private Date dateCreated;

    @Column(name = "updated_at")
    private Date dateUpdated;
}
