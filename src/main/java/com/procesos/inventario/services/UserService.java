package com.procesos.inventario.services;

import com.procesos.inventario.models.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> allUser();
    User createUser(User user);
    User updateUser(Long id,User user);
    String login(User user);
}
