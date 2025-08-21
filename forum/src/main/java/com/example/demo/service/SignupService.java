package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SignupData;
import com.example.demo.entity.SignupErrorMessage;

@Service
public class SignupService {
	private ValidationService validationService;
	
	public SignupService(ValidationService validationService) {
		this.validationService = validationService;
	}
	
	public SignupErrorMessage checkInputData(SignupData data) {
		SignupErrorMessage errorMessage = new SignupErrorMessage();
		// メールアドレスのチェック
		try {
			validationService.ValidatEmail(data.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage.setEmailError(e.getMessage());
		}
		
		// ニックネームのチェック
		try {
			validationService.ValidationNickname(data.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage.setNicknameError(e.getMessage());
		}
		
		// パスワードのチェック
		try {
			validationService.ValidationPassword(data.getPassword(), data.getPasswrodConfirm());
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage.setPasswordError(e.getMessage());
		}
		
		return errorMessage;
	}

}
