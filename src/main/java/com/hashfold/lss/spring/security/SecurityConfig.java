package com.hashfold.lss.spring.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication();// .withUser("user").password("password").roles("USER");
		auth.userDetailsService(inMemoryUserDetailsManager());

		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/delete/**").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/doLogin").and().logout().permitAll()
				.logoutUrl("/doLogout").clearAuthentication(true).and().csrf().disable();
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final Properties users = new Properties();

		users.put("user", "pass,ROLE_USER,enabled");
		users.put("admin", "pass,ROLE_ADMIN,enabled");

		return new InMemoryUserDetailsManager(users);
	}
}
