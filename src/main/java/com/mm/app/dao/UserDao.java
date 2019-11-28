package com.mm.app.dao;

import java.util.List;

import com.mm.app.model.User;

/**
 * @author nahid
 *
 */
public interface UserDao {

	public void createUser(User user);

	public User findUserById(Long id);

	public void deleteUserById(Long id);

	public List<User> findAllUsers();

	public User findUserByName(String name);
}
