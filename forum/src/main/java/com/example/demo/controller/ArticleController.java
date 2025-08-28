package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.LoginUser;

@SessionAttributes(types = LoginUser.class)
@Controller
@RequestMapping("/article")
public class ArticleController {
	private int id;
	
	public ArticleController() {
		id = 0;
	}
	
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}
	
	@GetMapping("")
	public String showArticle(@RequestParam("id") int id) {
		System.out.println(id);
		return "article/show";
	}
}
