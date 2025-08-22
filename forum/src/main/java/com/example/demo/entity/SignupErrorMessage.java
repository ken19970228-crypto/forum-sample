package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupErrorMessage implements ErrorMessage {
	private String email;
	private String nickname;
	private String password;
	
	public SignupErrorMessage() {
		email = "";
		nickname = "";
		password = "";
	}
	
	@Override
	public boolean hasError() {
		return !(email + nickname + password).equals("");
	}

}
