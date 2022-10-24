package com.KoreaIT.example.JAM.service;

import java.util.List;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int doWrite(String title, String body, int memberId) {
		return articleDao.doWrite(title, body, memberId);
	}

	public int doModify(int id, String title, String body) {
		return articleDao.doModify(id, title, body);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public boolean isArticleExists(int id) {
		return articleDao.isArticeExists(id);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
		
	}

}
