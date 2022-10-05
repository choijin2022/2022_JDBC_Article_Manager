package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("프로그램 시작");
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		int lastArticleId=0;
		while (true) {
			System.out.printf("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			else if(cmd.equals("article write")) {
				System.out.println(" == 게시물 작성 ==");
				
				int id = lastArticleId+1;
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				//sql 작성?
				
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");

					String sql = "INSERT INTO article";
					sql += " SET regDate = NOW()";
					sql += ", updateDate = NOW()";
					// title 
					sql += ", title = CONCAT('제목', RAND())";
					// body
					sql += ", `body` = CONCAT('내용', RAND());";

					pstmt = conn.prepareStatement(sql);

					pstmt.executeUpdate();

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (pstmt != null && !pstmt.isClosed()) {
							pstmt.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				//
//				Article article = new Article(id,title, body);
//				System.out.println(article);
//				articles.add(article);
//				lastArticleId++;
				
				
				System.out.printf(" %d 번 글이 생성되었습니다.\n",id );
			}
			else if(cmd.equals("article list")) {
				System.out.println(" == 게시물 리스트 ==");
				
				if(articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("id     |       제목");
				for(Article article : articles) {
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
				
			}
			
			

			

			
		}
		sc.close();
	}
}
