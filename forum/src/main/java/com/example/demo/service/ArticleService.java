package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public Article getArticleById(int article_id) {
		Article article = new Article();
		Map<String, Object> articleData = articleRepository.findByArticleId(article_id);
		
		article.setArticle_id(Integer.parseInt(articleData.get("article_id").toString()));
		article.setUser_id(Integer.parseInt(articleData.get("user_id").toString()));
		article.setUser_nickname(articleData.get("user_nickname").toString());
		article.setTitle(articleData.get("title").toString());
		article.setText(articleData.get("text").toString());
		article.setDate(articleData.get("date").toString().replace(".0", ""));
		return article;
	}
	
	public List<Article> getArticles(int num){
		List<Article> articles = new ArrayList<Article>();
		List<Map<String, Object>> articlesData = articleRepository.findLatest(num);
		for(Map<String, Object> data: articlesData) {
			Article article = new Article();
			for(String key: data.keySet()) {
				switch(key){
				case "article_id":
					article.setArticle_id(Integer.parseInt(data.get(key).toString()));
					break;
				case "user_id":
					article.setUser_id(Integer.parseInt(data.get(key).toString()));
					break;
				case "user_nickname":
					article.setUser_nickname(data.get(key).toString());
					break;
				case "title":
					article.setTitle(data.get(key).toString());
					break;
				case "text":
					article.setText(data.get(key).toString());
					break;
				case "date":
					article.setDate(data.get(key).toString());
					break;
				}
			}
			articles.add(article);
		}
		return articles;
	}
}
