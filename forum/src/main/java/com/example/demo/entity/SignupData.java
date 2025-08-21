package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupData {
	private String email;
	private String nickname;
	private String password;
	private String passwrodConfirm;
}
