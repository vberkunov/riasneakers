package com.shopservice.riasneakers.controllers;

import com.shopservice.riasneakers.dto.UserDto;
import com.shopservice.riasneakers.dto.response.AuthResponse;
import com.shopservice.riasneakers.dto.response.RegisterResponse;
import com.shopservice.riasneakers.services.serviceImpl.UserServiceImpl;
import com.shopservice.riasneakers.dto.response.RegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class.getSimpleName());
    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @PostMapping("admin/register")
    public RegisterResponse registerAdmin(@RequestBody UserDto registrationRequest) {
        logger.info("Register new admin in system");
        return userService.saveAdmin(registrationRequest);
    }

    @PostMapping("user/register")
    public RegisterResponse registerUser(@RequestBody UserDto registrationRequest) {
        logger.info("Register new user in system");
        return userService.saveUser(registrationRequest);
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody UserDto request) {
        logger.info("Authentication");
        return userService.authentificateUser(request);

    }

}
