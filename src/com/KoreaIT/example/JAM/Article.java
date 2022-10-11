package com.KoreaIT.example.JAM;

import java.util.Map;

public class Article extends Object {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}

	public Article(Map<String, Object> articleMap) {
		// 컬럼명이 key, 가져온 데이터 타입은 위에 명시된 Object 타입 --> 형변환 필요
//		this.id = articleMap.get("id");
//		this.regDate = articleMap.get("regDate");
//		this.updateDate = articleMap.get("updateDate");
//		this.title = articleMap.get("title");
//		this.body = articleMap.get("body");
//	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + "]";
	}
}