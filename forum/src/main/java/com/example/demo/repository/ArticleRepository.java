package com.example.demo.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginUser;

@Repository
public class ArticleRepository {
	private JdbcTemplate jdbcTemplate;
	public ArticleRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 記事登録
	public void create(String title, String text, LoginUser user) {
		String sql = "INSERT INTO articles (user_id, user_nickname, title, text, date)"
				+ "VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUser_id(), user.getNickname(), title, text, getTimeNow());
	}
	
	// ユーザーIDで最新の記事を取得
	public Map<String, Object> findByUserId(LoginUser user){
		String sql = "SELECT * FROM articles WHERE user_id = ? "
				+ "ORDER BY article_id DESC "
				+ "LIMIT 1";
		return jdbcTemplate.queryForMap(sql, user.getUser_id());
	}
	
	private String getTimeNow() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return LocalDateTime.now().format(format);
	}
}
