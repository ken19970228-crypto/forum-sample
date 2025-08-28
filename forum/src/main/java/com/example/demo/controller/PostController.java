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

import com.example.demo.entity.LoginUser;
import com.example.demo.service.PostService;

@SessionAttributes(types = LoginUser.class)
@Controller
@RequestMapping("/post")
public class PostController {
	private PostService postService;
	private String title;
	private String text;
	public PostController(PostService postService) {
		this.postService = postService;
		title = "";
		text = "";
	}
	
	@ModelAttribute(value = "loginUser")
	public LoginUser loginUser() {
		return new LoginUser();
	}
	
	@GetMapping("/article")
	public String articleInput(Model model,
			@ModelAttribute("errorMessage") String errorMessage) {
		
		model.addAttribute("title", title);
		model.addAttribute("text", text);
		return "article/post_input";
	}
	
	@GetMapping("/cancel")
	public String postCancel() {
		return "redirect:/";
	}
	
	@PostMapping("/article_confirm")
	public String articleConfirm(Model model,
			RedirectAttributes redirectAttributes,
			@RequestParam("title") String title,
			@RequestParam("text") String text) {
		
		this.title = title;
		this.text = text;
		try {
			postService.checkInput(title, text);
			model.addAttribute("title", title);
			model.addAttribute("text", text);
			return "article/post_confirm";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/post/article";
		}
	}
	
	@GetMapping("/return")
	public String returnInputPage() {
		return "redirect:/post/article";
	}
	
	@PostMapping("/article_post")
	public String postArticle(Model model,
			@ModelAttribute("loginUser") LoginUser loginUser) {
		
		try {
			postService.postArticle(title, text, loginUser);
			title = "";
			text = "";
			model.addAttribute("articleId", postService.getPostedArticleId(loginUser));
			return "article/post_comp";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("title", title);
			model.addAttribute("text", text);
			return "article/post_confirm";
		}
	}
}
