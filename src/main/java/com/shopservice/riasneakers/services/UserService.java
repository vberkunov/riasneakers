package com.shopservice.riasneakers.services;


import com.shopservice.riasneakers.dto.UserDto;
import com.shopservice.riasneakers.dto.response.AuthResponse;
import com.shopservice.riasneakers.dto.response.RegisterResponse;
import com.shopservice.riasneakers.entity.User;

public interface UserService {
     RegisterResponse saveAdmin(UserDto user);

     RegisterResponse saveUser(UserDto user);

     User findByLogin(String username);

     User findByLoginAndPassword(String login, String password);

     AuthResponse authentificateUser(UserDto request);
}
