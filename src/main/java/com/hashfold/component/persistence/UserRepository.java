package com.hashfold.component.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashfold.component.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
