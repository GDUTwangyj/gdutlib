package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDao {
	public Object addUser(User user);
	public boolean login(String name,String password);
	public List<User> query(String name);
}
