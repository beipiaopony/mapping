package com.noahark.mapping.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.noahark.mapping.bean.User;

@Repository("userDao")
public interface IUserDao {

	public static String APPLICATION_ADMIN="ADMIN";
	public static String APPLICATION_WORKER="WORKER";
	
	public User selectByPrimaryKey(int userId);
	
	public int selectRoleByUserName(@Param("appName") String app, @Param("userName") String userName);
}
