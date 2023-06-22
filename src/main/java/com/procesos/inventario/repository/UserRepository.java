package com.procesos.inventario.repository;

import com.procesos.inventario.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String Email,String Password); //tambien con or para si es uno o otro
}
