package com.ironhack.sporttracker.authservice.controller;

import com.ironhack.sporttracker.authservice.dto.NewUserDTO;
import com.ironhack.sporttracker.authservice.dto.UserDetailsDTO;
import com.ironhack.sporttracker.authservice.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user_details")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailsDTO getUserDetails(@RequestHeader (name="Authorization") String token){
            return userEntityService.getUserDetails(token);
    }


}
