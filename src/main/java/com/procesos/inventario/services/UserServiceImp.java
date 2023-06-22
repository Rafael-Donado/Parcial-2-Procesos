package com.procesos.inventario.services;

import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import com.procesos.inventario.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.procesos.inventario.utils.Constants.USER_NOT_FOUND;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public User getUserById (Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userDB = userRepository.findById(id).get();
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPassword(user.getPassword());
        userDB.setAddress(user.getAddress());
        userDB.setBirthday(user.getBirthday());
        return userRepository.save(userDB);
    }

    @Override
    public String login(User user) {
        Optional<User> userBd = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());//si es user,, en userRepository va User
        if (userBd.isEmpty()){
            throw new RuntimeException(USER_NOT_FOUND);
        }
        return jwtUtil.create(String.valueOf(userBd.get().getId()), String.valueOf(userBd.get().getEmail()));
    }

}
