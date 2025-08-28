package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
	private int user_id;
	private String email;
	private String nickname;
	
	public LoginUser() {
		user_id = 0;
		email = "";
		nickname = "";
	}
	
	public boolean isLogin() {
		if(email.equals(null))email="";
		if(nickname.equals(null))nickname="";
		return user_id!=0 && !(email + nickname).equals("");
	}
}
