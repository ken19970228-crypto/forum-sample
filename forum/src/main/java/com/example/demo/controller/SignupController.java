package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String showSignupInputPage(Model model,
			@ModelAttribute("errorMessage") SignupErrorMessage errorMessage) {
		
		model.addAttribute("data", data);
		return "/signup/input";
	}
	
	// キャンセル
	@GetMapping("/cancel")
	public String cancelSinup() {
		// 入力情報をクリア
		data = new SignupData();
		return "redirect:/";
	}
	
	// 入力情報の確認
	@PostMapping("/confirm")
	public String checkInputData(Model model,
			RedirectAttributes redirectAttributes,
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
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/signup/";
		}else {
			// エラーがない場合は確認画面へ遷移する
			model.addAttribute("data", data);
			return "/signup/confirm";
		}
	}
	
	// 確認から入力画面へ戻る
	@GetMapping("/return")
	public String retunInputPage() {
		return "redirect:/signup/";
	}
	
	@PostMapping("/registration")
	public String temporaryRegistration() {
		signupService.userTemporaryRegistration(data);
		// 入力情報をクリア
		data = new SignupData();
		return "/signup/sendMail";
	}
	
	// 本登録認証
	@GetMapping("/certification")
	public String registration(Model model,
			@RequestParam("code") String code) {
		try {
			signupService.userRegistration(code);
			model.addAttribute("error", "");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/signup/comp";
	}
}
