package com.KoreaIT.example.JAM.container;

import java.sql.Connection;

import com.KoreaIT.example.JAM.controller.ArticleController;
import com.KoreaIT.example.JAM.controller.MemberController;
import com.KoreaIT.example.JAM.dao.ArticleDao;
import com.KoreaIT.example.JAM.dao.MemberDao;
import com.KoreaIT.example.JAM.service.ArticleService;
import com.KoreaIT.example.JAM.service.MemberService;
import com.KoreaIT.example.JAM.session.Session;

public class Container {
	
public static ArticleController articleController;
public static MemberController memberController;

public static ArticleService articleService;
public static MemberService memberService;

public static ArticleDao articleDao;
public static MemberDao memberDao;

public Session session;

public static void init(Connection conn) {
	session = new Session();
}


}
