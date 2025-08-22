package com.example.demo.repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SignupData;

@Repository
public class RegisterRepository {
	private JdbcTemplate jdbcTemplate;
	public RegisterRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	// ユーザー仮登録
	public void temporaryCreate(SignupData data, String authentication_code) {
		String sql = "INSERT INTO temporary_users (email, nickname, password, authentication_code)"
				+ "VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, data.getEmail(), data.getNickname(), data.getPassword(), authentication_code);
	}
	// ユーザー本登録
	public void create(SignupData data) {
		String sql = "INSERT INTO users (email, nickname, password, authority)"
				+ "VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, data.getEmail(), data.getNickname(), data.getPassword(), "user");
	}
	// 仮登録ユーザー削除
	public void temporaryDeleteById(int id) {
		String sql = "DELETE FROM temporary_users WHERE user_id = ?";
		jdbcTemplate.update(sql, id);
	}
	// id指定でユーザー情報を取得
	public Map<String, Object> findById(int id){
		String sql = "SELECT *  FROM users WHERE user_id = ?";
		return jdbcTemplate.queryForMap(sql, id);
	}
	// 認証コードで取得
	public Map<String, Object> findeByCode(String code){
		String sql = "SELECT *  FROM temporary_users WHERE authentication_code = ?";
		return jdbcTemplate.queryForMap(sql, code);
	}
	
}
