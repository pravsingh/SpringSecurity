package com.hashfold.lss.persistence;

import com.hashfold.lss.web.model.User;

public interface UserRepository {

    Iterable<User> findAll();

    User save(User user);

    User findUser(Long id);

    void deleteUser(Long id);

}
