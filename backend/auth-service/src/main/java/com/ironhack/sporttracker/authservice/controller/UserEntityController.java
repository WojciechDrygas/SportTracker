package com.ironhack.sporttracker.authservice.controller;

import com.ironhack.sporttracker.authservice.dto.NewUserDTO;
import com.ironhack.sporttracker.authservice.dto.UserDetailsDTO;
import com.ironhack.sporttracker.authservice.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEntityController {

    private final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDetailsDTO createUser(@RequestBody NewUserDTO newUserDTO){
        return userEntityService.createUser(newUserDTO);
    }



}
