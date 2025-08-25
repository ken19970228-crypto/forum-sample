package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginErrorMessage;
import com.example.demo.entity.LoginUser;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {
	private ValidationService validationService;
	private UserRepository userRepository;
	public LoginService(
			ValidationService validationService,
			UserRepository userRepository) {
		
		this.validationService = validationService;
		this.userRepository = userRepository;
	}
	
	public LoginErrorMessage checkInputData(String email, String password) {
		LoginErrorMessage errorMessage = new LoginErrorMessage();
		// メールアドレスのチェック
		try {
			validationService.validatEmail(email);
		} catch (Exception e) {
			errorMessage.setEmail(e.getMessage());
		}
		
		// パスワードのチェック
		try {
			validationService.validationPassword(password);
		} catch (Exception e) {
			errorMessage.setPassword(e.getMessage());
		}
		
		try {
			passwordAuthentication(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage.setCertification(e.getMessage());
		}
		
		return errorMessage;
	}
	
	public void login(LoginUser loginUser, String email) {
		Map<String, Object> user = userRepository.findByEmail(email);
		loginUser.setEmail(user.get("email").toString());
		loginUser.setNickname(user.get("nickname").toString());
	}
	
	public void passwordAuthentication(String email, String password) throws Exception {
		Map<String, Object> user = userRepository.findByEmail(email);
		String decoded = decode(user.get("password").toString());
		if(!password.equals(decoded)) {
			// DBのパスワードと一致しなかった場合
			throw new Exception("パスワードが一致しません。");
		}
	}
	
	public String decode(String target) {
		Base64.Decoder decoder = Base64.getDecoder();
		String decoded = new String(decoder.decode(target.getBytes(StandardCharsets.UTF_8)));
		return decoded;
	}
}
