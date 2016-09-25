package com.hashfold.lss.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;

import com.hashfold.lss.persistence.InMemoryUserRepository;
import com.hashfold.lss.persistence.UserRepository;
import com.hashfold.lss.web.model.User;

@SpringBootApplication
@ComponentScan({ "com.hashfold.lss.web", "com.hashfold.lss.spring.security" })
public class Application {

	@Bean
	public UserRepository userRepository() {
		return new InMemoryUserRepository();
	}

	@Bean
	public Converter<String, User> messageConverter() {
		return new Converter<String, User>() {
			@Override
			public User convert(String id) {
				return userRepository().findUser(Long.valueOf(id));
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
