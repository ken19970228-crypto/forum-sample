package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
	private String email;
	private String nickname;
	
	public LoginUser() {
		email = "";
		nickname = "";
	}
	
	public boolean isLogin() {
		if(email.equals(null))email="";
		if(nickname.equals(null))nickname="";
		return !(email + nickname).equals("");
	}
}
