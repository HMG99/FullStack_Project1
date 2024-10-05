package com.digi.repository;

import com.digi.model.User;

public interface UserRepository {
    User getUserById(int id);
    User getUserByEmail(String email);
    void saveUser(User user);
}
