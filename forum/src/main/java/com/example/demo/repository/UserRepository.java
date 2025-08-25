package com.example.demo.repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private JdbcTemplate jdbcTemplate;
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// id指定でユーザー情報を取得
	public Map<String, Object> findByEmail(String email){
		String sql = "SELECT *  FROM users WHERE email = ?";
		return jdbcTemplate.queryForMap(sql, email);
	}
}
