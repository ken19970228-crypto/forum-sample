package com.example.demo.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
		jdbcTemplate.update(sql, user.getUser_id(), user.getNickname(), title, text.replace("\r\n", "<br>"), getTimeNow());
	}
	
	// 記事IDで記事を取得
	public Map<String, Object> findByArticleId(int article_id){
		String sql = "SELECT * FROM articles WHERE article_id = ? ";
		return jdbcTemplate.queryForMap(sql, article_id);
	}
	
	// ユーザーIDで最新の記事を取得
	public Map<String, Object> findByUserId(LoginUser user){
		String sql = "SELECT * FROM articles WHERE user_id = ? "
				+ "ORDER BY article_id DESC "
				+ "LIMIT 1";
		return jdbcTemplate.queryForMap(sql, user.getUser_id());
	}
	
	// 最新の記事を指定した件数取得
	public List<Map<String, Object>> findLatest(int num){
		String sql = "SELECT * FROM articles "
				+ "ORDER BY date DESC "
				+ "LIMIT ?";
		return jdbcTemplate.queryForList(sql, num);
	}
	
	private String getTimeNow() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return LocalDateTime.now().format(format);
	}
}
