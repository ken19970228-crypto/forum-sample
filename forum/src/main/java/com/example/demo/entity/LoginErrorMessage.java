package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginErrorMessage implements ErrorMessage {
	private String email;
	private String password;
	private String certification;
	
	public LoginErrorMessage() {
		email = "";
		password = "";
		certification = "";
	}
	
	@Override
	public boolean hasError() {
		if(email.equals(null))email="";
		if(password.equals(null))password="";
		if(certification.equals(null))certification="";
		return !(email + password + certification).equals("");
	}
}
