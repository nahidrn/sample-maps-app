package com.mm.app.dao;

import com.mm.app.model.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(Long id);
}
