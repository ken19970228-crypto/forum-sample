package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
	private int article_id;
	private int user_id;
	private String user_nickname;
	private String title;
	private String text;
	private String date;
}
