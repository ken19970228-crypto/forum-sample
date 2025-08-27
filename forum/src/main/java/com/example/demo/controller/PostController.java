package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.LoginUser;

@SessionAttributes(types = LoginUser.class)
@Controller
@RequestMapping("/post")
public class PostController {
	
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}
	
	@GetMapping("/article")
	public String articleInput() {
		return "article/post_input";
	}
}
