package com.noahark.mapping.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noahark.mapping.bean.User;
import com.noahark.mapping.dao.IUserDao;
import com.noahark.mapping.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findOne(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserRole(String appName, String user) {
		String app = "HFM:" + appName.toUpperCase();
		int count = userDao.selectRoleByUserName(app, user);
		if (count > 0 ) {
			return IUserDao.APPLICATION_ADMIN;
		} else {
			return IUserDao.APPLICATION_WORKER;
		}
		 
	}

	@Override
	public boolean isAdmin(String role) {
		return (IUserDao.APPLICATION_ADMIN.equals(role));
	}

	

}
