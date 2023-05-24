package com.procesos.inventario.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private Integer car_model_year;
    private String car_vin;
    private String price;
    private boolean availability;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
