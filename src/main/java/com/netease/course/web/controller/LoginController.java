package com.netease.course.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.meta.Result;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.service.ProductService;
import com.netease.course.service.impl.LoginServiceDBImpl;
import com.netease.course.service.impl.ProductServiceDBImpl;

/**
 * @author: atwjsw
 * @Description: web controller for login and error handling web access.
 * @Date: Apr 20, 2016 3:50:07 PM
 */
@Controller
public class LoginController {

	private ProductService productService;

	private LoginService loginService;

	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	public void setUserService(LoginServiceDBImpl loginServiceDBImpl) {
		this.loginService = loginServiceDBImpl;
	}

	@Autowired
	public void setProductService(ProductServiceDBImpl productServiceDBImpl) {
		this.productService = productServiceDBImpl;
	}

	@RequestMapping("/login")
	public String doLogin(HttpSession session) {
		System.out.println("login");
		session.removeAttribute("user");
		return "login";
	}

	@RequestMapping("/logout")
	public String doLogout(HttpSession session) {
		logger.debug("/logout");
		session.removeAttribute("user");
		return "login";
	}

	@RequestMapping("/api/login")
	@ResponseBody
	public Result doApiLogin(@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "password", required = false) String password, HttpSession session, ModelMap map)
					throws IOException {

		logger.debug("/api/login");
		User user = null;
		Result rs = new Result();

		// 如果是来自登录网页的请求，则调用登录服务获取User对象，
		if (userName != null && password != null) {
			user = loginService.getUser(userName, password);
			// 直接访问的请求，则尝试获取session里面的User对象，
		} else {
			user = (User) session.getAttribute("user");
		}

		// 通过以上逻辑根据获取的对象如果存在，则前往用户欢迎页面，否则前往登录错误页面
		if (user != null) {
			session.setAttribute("user", user);
			map.addAttribute("productList", productService.getProductList());
			map.addAttribute("user", user);
			rs.setCode(200);
			rs.setResult(true);
		} else {
			session.removeAttribute("user");
			rs.setCode(400);
			rs.setMessage("登录失败");
			rs.setResult(false);
		}
		return rs;

	}

	@RequestMapping("/error")
	public String doError(ModelMap map, HttpServletResponse res) {

		logger.debug("in /error status code " + res.getStatus());
		String errorMsg = null;
		if (res.getStatus() == 404)
			errorMsg = "您请求的资源不存在。";
		else {
			errorMsg = "出现系统错误，请稍后再试。";
		}
		map.addAttribute("errorMsg", errorMsg);
		return "error";
	}

}
