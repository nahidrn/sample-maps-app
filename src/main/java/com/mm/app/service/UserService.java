package com.mm.app.service;

import java.util.List;

import com.mm.app.model.User;

/**
 * @author nahid
 *
 */
public interface UserService {

    User findUserById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    User findUserByName(String name);

    boolean isUserNameUnique(Long id, String name);

}
