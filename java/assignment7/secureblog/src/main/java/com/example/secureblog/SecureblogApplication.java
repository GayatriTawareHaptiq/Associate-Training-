package com.example.secureblog;

import com.example.secureblog.model.User;
import com.example.secureblog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecureblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureblogApplication.class, args);
	}

	// This will NOT run when profile is "test"
	@Bean
	@Profile("!test")
	public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			User admin = new User("admin", passwordEncoder.encode("password"), "ROLE_ADMIN");
			userRepository.save(admin);

			User user = new User("user", passwordEncoder.encode("password"), "ROLE_USER");
			userRepository.save(user);
		};
	}
}
