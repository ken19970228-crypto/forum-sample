package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Article;
import com.example.demo.entity.LoginUser;
import com.example.demo.service.ArticleService;

@SessionAttributes(types = LoginUser.class)
@Controller
@RequestMapping("/article")
public class ArticleController {
	private int id;
	private ArticleService articleService;
	public ArticleController(ArticleService articleService) {
		id = 0;
		this.articleService = articleService;
	}
	
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}
	
	@GetMapping("")
	public String showArticle(Model model,
			@RequestParam("id") int id) {
		this.id = id;
		Article article = articleService.getArticleById(this.id);
		model.addAttribute("article", article);
		return "article/show";
	}
}
