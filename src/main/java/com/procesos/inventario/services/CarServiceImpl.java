package com.procesos.inventario.services;


import com.procesos.inventario.models.Car;
import com.procesos.inventario.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(Long id){
        return carRepository.findById(id).get();
    }

    public Car updateCar (Long id, Car car){
        Car carDB = carRepository.findById(id).get();
        carDB.setCar(car.getCar());
        carDB.setCar_model(car.getCar_model());
        carDB.setCar_vin(carDB.getCar_vin());
        carDB.setCar_color(carDB.getCar_color());
        carDB.setPrice(car.getPrice());
        carDB.setCar_model_year(car.getCar_model_year());
        carDB.setAvailability(carDB.isAvailability());
        return carRepository.save(carDB);
    }

}

