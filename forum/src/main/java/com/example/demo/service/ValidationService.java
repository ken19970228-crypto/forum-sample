package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {
	public void ValidatEmail(String email) throws Exception {
		if(!email.matches("^[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+$")) {
			// メールアドレスの形式が正しくない場合
			throw new Exception("メールアドレスの形式が正しくありません。");
		}
	}
	public void ValidationNickname(String nickname) throws Exception {
		if(nickname.length() < 8) {
			// ニックネームが短い場合
			throw new Exception("ニックネームは8文字以上で入力してください。");
		}else if(nickname.length() > 32){
			// ニックネームが長い場合
			throw new Exception("ニックネームは32文字以内で入力してください。");
		}
	}
	public void ValidationPassword(String password, String passwordConfirm) throws Exception {
		if(password.length() < 10) {
			// ニックネームが短い場合
			throw new Exception("パスワードは10文字以上で入力してください。");
		}else if(password.length() > 32){
			// ニックネームが長い場合
			throw new Exception("パスワードは32文字以内で入力してください。");
		}else if(!password.matches("^[a-zA-Z0-9]{10,}$")) {
			// 半角英数字以外が入力されていた場合
			throw new Exception("パスワードは半角英数字のみで入力してください。");
		}else if(!password.equals(passwordConfirm)) {
			// パスワードが不一致の場合
			throw new Exception("パスワードが一致しません。");
		}
	}
}
