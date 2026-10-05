package com.bmk.portfolio.config;

import com.bmk.portfolio.model.User;
import com.bmk.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${admin.username}") String adminUsername,
            @Value("${admin.password}") String adminPassword) {

        return args -> {

            System.out.println(">>> DataInitializer RUNNING");
            System.out.println(">>> ADMIN USERNAME = " + adminUsername);

            if (userRepository.findByUsername(adminUsername).isEmpty()) {

                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println(">>> ADMIN CREATED");
            } else {
                System.out.println(">>> ADMIN ALREADY EXISTS");
            }
        };
    }
}