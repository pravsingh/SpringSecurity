package com.hashfold.lss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hashfold.component.model.user.User;
import com.hashfold.component.persistence.UserRepository;
import com.hashfold.component.security.WebMvcConfiguration;

@SpringBootApplication
@ComponentScan({ "com.hashfold.component" })
@EnableJpaRepositories("com.hashfold.component.persistence")
@Import({ WebMvcConfiguration.class })
@EntityScan("com.hashfold.component.model.user")
public class Application {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public Converter<String, User> messageConverter() {
		return new Converter<String, User>() {
			@Override
			public User convert(String id) {
				return userRepository.findOne(Long.valueOf(id));
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
