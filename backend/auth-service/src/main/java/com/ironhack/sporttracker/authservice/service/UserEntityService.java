package com.ironhack.sporttracker.authservice.service;

import com.ironhack.sporttracker.authservice.dao.UserEntity;
import com.ironhack.sporttracker.authservice.dto.NewUserDTO;
import com.ironhack.sporttracker.authservice.dto.UserDetailsDTO;
import com.ironhack.sporttracker.authservice.repository.UserEntityRepository;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserEntityService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final Environment environment;
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private ModelMapper modelMapper;

    public UserEntityService(UserEntityRepository userEntityRepository, Environment environment, BCryptPasswordEncoder encoder) {
        this.userEntityRepository = userEntityRepository;
        this.environment = environment;
        this.encoder = encoder;
    }

    public UserDetailsDTO createUser(NewUserDTO newUserDTO) {
        Optional<UserEntity> userByEmail = userEntityRepository.findByEmail(newUserDTO.getEmail());
        if (userByEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User email already exists!");
        }
        Optional<UserEntity> userByUsername = userEntityRepository.findByUsername(newUserDTO.getUsername());
        if (userByUsername.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User login already exists!");
        }
        newUserDTO.setPassword(encoder.encode(newUserDTO.getPassword()));
        UserEntity userSaved = userEntityRepository.save(modelMapper.map(newUserDTO,UserEntity.class));
        return modelMapper.map(userSaved,UserDetailsDTO.class);
    }

    public UserDetailsDTO getUserDetails(String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Optional<UserEntity> userEntity = userEntityRepository.findById(tokenSubject);
        if (userEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }else{
            return modelMapper.map(userEntity.get(),UserDetailsDTO.class);
        }
    }

    public UserDetailsDTO getUserDetailsByUsername(String username) {
        Optional<UserEntity> user = userEntityRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to login!");
        }else{
            return modelMapper.map(user.get(),UserDetailsDTO.class);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userEntityRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to login!");
        }
        else{
            return new User(user.get().getUsername(),user.get().getPassword(), true, true, true, true, new ArrayList<>());
        }
    }
}
