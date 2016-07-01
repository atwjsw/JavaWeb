package com.netease.course.meta;

/**
 * @author: atwjsw
 * @Description: Data container for user object
 * @Date: Apr 20, 2016 3:31:05 PM
 */
public class User {
	private int id;
	private String username;
	private String nickname;
	private int usertype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
}
