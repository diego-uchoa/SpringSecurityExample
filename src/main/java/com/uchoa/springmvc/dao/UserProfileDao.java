package com.uchoa.springmvc.dao;

import java.util.List;

import com.uchoa.springmvc.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
