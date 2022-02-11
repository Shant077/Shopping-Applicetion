package com.example.demo;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//        return args -> {
//            Users aaa =new Users(
//                    getName(),
//                    "Abgaryan",
//                    "aaa@mail.ru",
//                    "shshsh"
//            );
//            aaa.getName()
//            userRepository.save(aaa);
//        };
//    }
}
