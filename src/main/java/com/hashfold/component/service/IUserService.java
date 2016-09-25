package com.hashfold.component.service;

import com.hashfold.component.model.user.User;
import com.hashfold.component.validation.EmailExistsException;

public interface IUserService {

	User registerNewUser(User user) throws EmailExistsException;

}