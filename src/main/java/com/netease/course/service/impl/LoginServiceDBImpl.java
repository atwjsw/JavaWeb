package com.netease.course.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.LoginDao;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;

/**
 * @author: atwjsw
 * @Description: Service implementation for login service interface. 
 * @Date: Apr 20, 2016 3:35:38 PM
 */
@Service
public class LoginServiceDBImpl implements LoginService {

	private Logger logger = Logger.getLogger(LoginServiceDBImpl.class);

	@Autowired
	private LoginDao loginDao;

	public void setUserDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public User getUser(String userName, String userPassword) {
		logger.debug("In LoginServiceDBImpl");
		return loginDao.selectUser(userName, userPassword);
	}

}
