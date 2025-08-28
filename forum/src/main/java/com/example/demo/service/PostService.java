package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.LoginUser;
import com.example.demo.repository.ArticleRepository;

@Service
public class PostService {
	private ArticleRepository articleRepository;
	public PostService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public void checkInput(String title, String text) throws Exception {
		final int TITLE_MAX = 300;
		final int TEXT_MAX = 3000;
		if(title.equals(null) || title.equals("")) {
			throw new Exception("タイトルを入力してください。");
		}else if(title.length() > TITLE_MAX) {
			throw new Exception("タイトルは300文字以内で入力してください。");
		}else if(text.equals(null) || text.equals("")) {
			throw new Exception("本文を入力してください。");
		}else if(text.length() > TEXT_MAX) {
			throw new Exception("本文は3000文字以内で入力してください。");
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void postArticle(String title, String text, LoginUser user) {
		articleRepository.create(title, text, user);
	}
	
	public int getPostedArticleId(LoginUser user) {
		return Integer.parseInt(articleRepository.findByUserId(user).get("article_id").toString());
	}
}
