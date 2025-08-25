package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SignupData;
import com.example.demo.entity.SignupErrorMessage;
import com.example.demo.repository.RegisterRepository;

@Service
public class SignupService {
	private ValidationService validationService;
	private RegisterRepository registerRepository;
	private JavaMailSender mailSender;
	public SignupService(
			ValidationService validationService,
			RegisterRepository registerRepository,
			JavaMailSender mailSender) {
		
		this.validationService = validationService;
		this.registerRepository = registerRepository;
		this.mailSender = mailSender;
	}
	
	public SignupErrorMessage checkInputData(SignupData data) {
		SignupErrorMessage errorMessage = new SignupErrorMessage();
		// メールアドレスのチェック
		try {
			validationService.validatEmail(data.getEmail());
		} catch (Exception e) {
			errorMessage.setEmail(e.getMessage());
		}
		
		// ニックネームのチェック
		try {
			validationService.validationNickname(data.getNickname());
		} catch (Exception e) {
			errorMessage.setNickname(e.getMessage());
		}
		
		// パスワードのチェック
		try {
			validationService.validationPassword(data.getPassword(), data.getPasswrodConfirm());
		} catch (Exception e) {
			errorMessage.setPassword(e.getMessage());
		}
		
		return errorMessage;
	}
	@Transactional(rollbackFor=Exception.class)
	public void userTemporaryRegistration(SignupData data) {
		
		data.setPassword(encode(data.getPassword()));
		String authenticationCode = encode(data.getEmail());
		registerRepository.temporaryCreate(data, authenticationCode);
		sendMail(data, authenticationCode);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void userRegistration(String code) throws Exception {
		System.out.println(code);
		try {
			Map<String, Object> userInfo = registerRepository.findeByCode(code);
			SignupData data = new SignupData();
			data.setEmail(userInfo.get("email").toString());
			data.setNickname(userInfo.get("nickname").toString());
			data.setPassword(userInfo.get("password").toString());
			registerRepository.create(data);
			System.out.println(userInfo.get("user_id").toString());
			registerRepository.temporaryDeleteById(Integer.parseInt(userInfo.get("user_id").toString()));
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("認証に失敗しました。");
		}
	}
	
	public String encode(String target) {
		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString(target.getBytes(StandardCharsets.UTF_8));
		return encoded;
	}
	
	public String decode(String target) {
		Base64.Decoder decoder = Base64.getDecoder();
		String decode = new String(decoder.decode(target.getBytes(StandardCharsets.UTF_8)));
		return decode;
	}
	
	public void sendMail(SignupData data, String authenticationCode) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("本登録のご案内");
		message.setText("会員登録の手続きありがとうございます。\n"
				+ "下記アドレスにアクセスし、本登録の手続きをお願い致します。\n"
				+ "http://www.localhost:8080/signup/certification?code=" + authenticationCode);
		message.setTo(data.getEmail());
		message.setFrom("samplForum@test.com");
		mailSender.send(message);
	}
}
