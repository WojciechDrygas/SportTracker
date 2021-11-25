package com.ironhack.sporttracker.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
    private String login;
    private String password;
    private String name;
    private String email;
}
