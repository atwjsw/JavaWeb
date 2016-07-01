package com.netease.course.service;

import com.netease.course.meta.User;

/**
 * @author: atwjsw
 * @Description: Service interface for login related service
 * @Date: Apr 20, 2016 3:32:01 PM
 */
public interface LoginService {
	public User getUser(String userName, String userPassword);
}