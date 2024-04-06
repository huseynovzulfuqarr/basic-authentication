package com.example.basicauthentication;

import com.example.basicauthentication.dto.UserRequestDto;
import com.example.basicauthentication.entity.Role;
import com.example.basicauthentication.entity.User;
import com.example.basicauthentication.repository.UserRepository;
import com.example.basicauthentication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class BasicauthenticationApplication implements CommandLineRunner {

    private final UserService userService;



    public static void main(String[] args) {
        SpringApplication.run(BasicauthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("user created,{}");
        createUser();

    }

    private void createUser(){

        UserRequestDto user=UserRequestDto.builder()
                .name("test")
                .username("user")
                .password("123")
                .authorities(Set.of(Role.USER))
                .build();
        this.userService.createUser(user);

        UserRequestDto admin =UserRequestDto.builder()
                .name("test")
                .username("admin")
                .password("123")
                .authorities(Set.of(Role.ADMIN))
                .build();
        this.userService.createUser(admin);
        log.info("user adn admin created wit userService {}" ,user) ;



    }
}
