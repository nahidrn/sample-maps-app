package com.mm.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mm.app.dao.UserDao;
import com.mm.app.model.User;
import com.mm.app.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User findUserById(Long id) {
    	
        return dao.findUserById(id);
    }

    public void createUser(User user) {
    	
        dao.createUser(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
    	
        User entity = dao.findUserById(user.getId());
        if(entity!=null){
            entity.setName(user.getName());
            entity.setPassword(user.getPassword());
            entity.setActive(user.isActive());
        }
    }

    public void deleteUserById(Long id) {
    	
        dao.deleteUserById(id);
    }

    public List<User> findAllUsers() {
    	
        return dao.findAllUsers();
    }

    public User findUserByName(String name) {
    	
        return dao.findUserByName(name);
    }

    public boolean isUserNameUnique(Long id, String name) {
    	
        User user = findUserByName(name);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }

}