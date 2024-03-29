package com.shrihari.service;

import com.shrihari.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);
}
