package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Article;
import com.example.demo.entity.LoginUser;
import com.example.demo.service.ArticleService;

@SessionAttributes(types = LoginUser.class)
@Controller
public class TopController {
	private ArticleService articleService;
	
	public TopController(ArticleService articleService) {
		this.articleService = articleService;
	}
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}

	@GetMapping("/")
	public String showTop(Model model,
			@ModelAttribute("loginUser") LoginUser loginUser) {
		
		List<Article> articles = articleService.getArticles(5);
		model.addAttribute("articles", articles);
		return "top";
	}
	
	@GetMapping("/logout")
	public String logout(@ModelAttribute("loginUser") LoginUser loginUser) {
		// ログイン情報をリセット
		loginUser.setEmail("");
		loginUser.setNickname("");
		loginUser.setUser_id(0);
		return "redirect:/";
	}
}
