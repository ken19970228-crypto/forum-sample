package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.LoginErrorMessage;
import com.example.demo.entity.LoginUser;
import com.example.demo.service.LoginService;

@SessionAttributes(types = LoginUser.class)
@Controller
@RequestMapping("/login")
public class LoginController {
	private LoginService loginService;
	private String email;
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
		email = "";
	}
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}
	
	@GetMapping("/")
	public String showLoginPage(Model model,
			@ModelAttribute("errorMessage") LoginErrorMessage errorMessage) {
		
		model.addAttribute("email", email);
		return "login";
	}
	
	// キャンセル
	@PostMapping("/cancel")
	public String cancelLogin() {
		email = "";
		return "redirect:/";
	}
	
	// ログイン実行
	@PostMapping("/execution")
	public String executionLogin(RedirectAttributes redirectAttributes,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@ModelAttribute("loginUser") LoginUser loginUser) {
		
		loginUser.setEmail("");
		loginUser.setNickname("");
		loginUser.setUser_id(0);
		this.email = email;
		LoginErrorMessage errorMessage = loginService.checkInputData(email, password);
		if(errorMessage.hasError()) {
			// 入力・認証にエラーがある場合はログイン画面に戻る
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/login/";
		}else {
			// メールアドレス・パスワードの認証に問題なかった場合はログイン情報を保持してトップページに遷移
			loginService.login(loginUser, email);
			this.email = "";
			return "redirect:/";
		}
	}
}
