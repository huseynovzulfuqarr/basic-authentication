package com.example.basicauthentication.service;

import com.example.basicauthentication.dto.UserRequestDto;
import com.example.basicauthentication.entity.User;
import com.example.basicauthentication.exception.UserNotFoundException;
import com.example.basicauthentication.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> getByUsername(String username){
        return this.userRepository.findByUsername(username);

    }

    public User createUser(UserRequestDto userRequestDto){

        User user=User.builder()
                .name(userRequestDto.name())
                .username(userRequestDto.username())
                .password(passwordEncoder.encode(userRequestDto.password()))
                .authorities(userRequestDto.authorities())
                .build();
        log.info("saved user {}",user);
        return this.userRepository.save(user);

    }





}
