package com.ironhack.sporttracker.authservice.service;

import com.ironhack.sporttracker.authservice.dao.UserEntity;
import com.ironhack.sporttracker.authservice.dto.NewUserDTO;
import com.ironhack.sporttracker.authservice.dto.UserDetailsDTO;
import com.ironhack.sporttracker.authservice.repository.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    public UserDetailsDTO createUser(NewUserDTO newUserDTO) {
        Optional<UserEntity> userByEmail = userEntityRepository.findByEmail(newUserDTO.getEmail());
        if (userByEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User email already exists!");
        }
        Optional<UserEntity> userByLogin = userEntityRepository.findByLogin(newUserDTO.getLogin());
        if (userByLogin.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User login already exists!");
        }
        UserEntity userSaved = userEntityRepository.save(modelMapper.map(newUserDTO,UserEntity.class));
        return modelMapper.map(userSaved,UserDetailsDTO.class);
    }
}
