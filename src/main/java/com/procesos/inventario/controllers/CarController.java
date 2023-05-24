package com.procesos.inventario.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procesos.inventario.models.Car;
import com.procesos.inventario.services.CarService;
import com.procesos.inventario.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/cars/{id}")
    public Car createAllCars(@PathVariable Long id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String carrosApi = restTemplate.getForObject("https://myfakeapi.com/api/cars/" + id, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(carrosApi.substring(7), Car.class);

        Car carroguardado = carService.createCar(car);
        return carroguardado;
    }
    @PostMapping(value = "/car")
    public ResponseEntity createProduct(@RequestBody Car car, @RequestHeader(value="Authorization") String token){
        Map response = new HashMap();
        try{
            if(!validateToken(token)){
                return new ResponseEntity("token invalido", HttpStatus.UNAUTHORIZED);
            }
            response.put("message","Vehiculo creado correctamente");
            response.put("data",carService.createCar(car));
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message","Error al crear vehiculo");
            response.put("data",e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/cars")
    public ResponseEntity getAll(@RequestHeader(value="Authorization") String token) {
        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("token invalido", HttpStatus.UNAUTHORIZED);
            }
            List<Car> carList = carService.getAll();
            response.put("message", "Se encontraron los carros");
            response.put("data", carService.getAll());
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("message", "No se encontraron los carros");
            response.put("data", null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/cars/{id}")
    public ResponseEntity getCar(@PathVariable(name = "id") Long id, @RequestHeader(value="Authorization") String token) {
        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("token invalido", HttpStatus.UNAUTHORIZED);
            }
            response.put("mensaje", "Se encontro el carro");
            response.put("data", carService.getCarById(id));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al buscar el carro");
            response.put("data", e.getMessage());
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/cars/{id}")
    public ResponseEntity updateCar(@PathVariable(name = "id") Long id, @RequestBody Car car, @RequestHeader(value="Authorization") String token) {
        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("token invalido", HttpStatus.UNAUTHORIZED);
            }
            response.put("message", "Carro actualizado correctamente");
            response.put("data", carService.updateCar(id, car));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "El carro no se encontro");
            response.put("data", id);
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }
    private Boolean validateToken(String token){
        try{
            if(jwtUtil.getKey(token) != null){
                return true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }
}