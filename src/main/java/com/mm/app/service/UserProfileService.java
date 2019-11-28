package com.mm.app.service;

import com.mm.app.model.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(Long id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
