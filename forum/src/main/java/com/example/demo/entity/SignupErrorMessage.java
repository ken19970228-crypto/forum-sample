package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupErrorMessage implements ErrorMessage {
	private String emailError;
	private String nicknameError;
	private String passwordError;
	
	public SignupErrorMessage() {
		emailError = "";
		nicknameError = "";
		passwordError = "";
	}
	
	@Override
	public boolean hasError() {
		return !(emailError + nicknameError + passwordError).equals("");
	}

}
