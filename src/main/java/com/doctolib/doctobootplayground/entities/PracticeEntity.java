package com.doctolib.doctobootplayground.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "practices")
public class PracticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    Long organizationId;

    @Column
    String address;

    @Column
    String city;

    @Column
    String zipcode;

    @Column
    Long longitude;

    @Column
    Long latitude;
}
