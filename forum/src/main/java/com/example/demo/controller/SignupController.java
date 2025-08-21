package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.SignupData;
import com.example.demo.entity.SignupErrorMessage;
import com.example.demo.service.SignupService;

@Controller
@RequestMapping("signup")
public class SignupController {
	private SignupService signupService;
	private SignupData data;
	public SignupController(SignupService signupService) {
		this.signupService = signupService;
		data = new SignupData();
	}
	
	// 情報入力ページ表示
	@GetMapping("/")
	public String showSignupInputPage(Model model) {
		model.addAttribute("email", data.getEmail());
		model.addAttribute("nickname", data.getNickname());
		model.addAttribute("password", data.getPassword());
		return "/signup/input";
	}
	
	// キャンセル
	@GetMapping("/cancel")
	public String cancelSinup() {
		return "redirect:/";
	}
	
	// 入力情報の確認
	@PostMapping("/confirm")
	public String checkInputData(RedirectAttributes redirectAttributes,
			@RequestParam("email") String email,
			@RequestParam("nickname") String nickname,
			@RequestParam("password") String password,
			@RequestParam("password_confirm") String passwordConfirm) {
		
		data.setEmail(email);
		data.setNickname(nickname);
		data.setPassword(password);
		data.setPasswrodConfirm(passwordConfirm);
		SignupErrorMessage errorMessage = signupService.checkInputData(data);
		
		if(errorMessage.hasError()) {
			// エラーがある場合は入力画面に戻る
			redirectAttributes.addFlashAttribute("email_error", errorMessage.getEmailError());
			redirectAttributes.addFlashAttribute("nickname_error", errorMessage.getNicknameError());
			redirectAttributes.addFlashAttribute("password_error", errorMessage.getPasswordError());
			return "redirect:/signup/";
		}else {
			// エラーがない場合は確認画面へ遷移する
			return "/";
		}
		
	}
}
