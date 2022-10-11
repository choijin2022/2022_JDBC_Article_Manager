package com.KoreaIT.example.JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Article extends Object {
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Article(int id, LocalDateTime regDate, LocalDateTime updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}

	public Article(Map<String, Object> articleMap) {
		// 컬럼명이 key, 가져온 데이터 타입은 위에 명시된 Object 타입 --> 형변환 필요
		this.id = (int)articleMap.get("id");
		this.regDate = (LocalDateTime)articleMap.get("regDate");
		this.updateDate = (LocalDateTime)articleMap.get("updateDate");
		this.title = (String)articleMap.get("title");
		this.body = (String)articleMap.get("body");
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + "]";
	}
}