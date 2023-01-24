package com.orgofarmsgroup;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PerfectAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectAppApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return (args) -> {
			UserEntity user = new UserEntity(101L, "John", "john@email.com");
			userRepository.save(user);
		};
	}

}
