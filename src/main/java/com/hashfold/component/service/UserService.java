package com.hashfold.component.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashfold.component.model.user.User;
import com.hashfold.component.persistence.UserRepository;
import com.hashfold.component.validation.EmailExistsException;

@Service
@Transactional
class UserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User registerNewUser(final User user) throws EmailExistsException {
		if (emailExist(user.getEmail())) {
			throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
		}
		return repository.save(user);
	}

	private boolean emailExist(String email) {
		final User user = repository.findByEmail(email);
		return user != null;
	}

}