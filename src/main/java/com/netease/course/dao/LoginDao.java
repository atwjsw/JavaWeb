package com.netease.course.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.netease.course.meta.User;

/**
 * @author: atwjsw
 * @Description: Data access object for login related database operation.
 * @Date: Apr 20, 2016 3:21:13 PM
 */
public interface LoginDao {

	@Select("select id, username, password, usertype, nickname from person where userName=#{userName} and password=#{password}")
	public User selectUser(@Param("userName") String userName, @Param("password") String password);

}
