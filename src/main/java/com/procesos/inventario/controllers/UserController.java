package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserService;
import com.procesos.inventario.services.UserServiceImp;
import com.procesos.inventario.utils.ApiResponse;
import com.procesos.inventario.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;
    Map data = new HashMap<>();

    @GetMapping(value = "/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        try{
            apiResponse = new ApiResponse(Constants.REGISTER_FOUNT, userService.getUser(id));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
           }catch (Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUNT,"");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "")
    public ResponseEntity saveUser(@RequestBody User user){
        Boolean userRes = userService.createUser(user);
        if(userRes == true){
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, "");
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD, user);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "")
    public ResponseEntity findUsers() {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_LIST, userService.allUsers());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUNT, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping (value = "/{id}")
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody User user){
        Boolean userResp = userService.updateUser(id, user);
        if (userResp){
            apiResponse = new ApiResponse(Constants.ERROR_DATA, "");
            return new ResponseEntity(apiResponse, HttpStatus.PAYMENT_REQUIRED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_UPDATE, "");
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }

}

