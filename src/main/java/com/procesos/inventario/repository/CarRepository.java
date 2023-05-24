package com.procesos.inventario.repository;

import com.procesos.inventario.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository <Car,Long> {

}
