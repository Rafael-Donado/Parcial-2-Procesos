package com.procesos.inventario.services;

import com.procesos.inventario.models.Car;


import java.util.List;


public interface CarService {


    Car createCar(Car car);

    List<Car> getAll();

    Car getCarById (Long id);

    Car updateCar(Long id,Car car);
}
