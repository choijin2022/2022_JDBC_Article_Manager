package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("프로그램 시작");
		Scanner sc = new Scanner(System.in);

//		List<Article> articles = new ArrayList<>();

		int lastArticleId = 0;
		while (true) {
			System.out.printf("명령어 > ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			} else if (cmd.equals("article write")) {
				System.out.println(" == 게시물 작성 ==");

				int id = lastArticleId + 1;

				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				// sql 작성?

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
					sql += ", title = '" + title + "'";

					sql += ", `body` = '" + body + "';";

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
//				articles.add(article);
				lastArticleId++;

				System.out.printf(" %d 번 글이 생성되었습니다.\n", id);
			}
			// [List]
			else if (cmd.equals("article list")) {
				System.out.println(" == 게시물 리스트 ==");

				//
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				List<Article> articles = new ArrayList<>();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");

					String sql = "SELECT * ";
					sql += " FROM article";
					sql += " ORDER BY id DESC;";

					System.out.println(sql);

					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					// select한 데이터 꺼내오기
					while (rs.next()) {
						int id = rs.getInt("id"); // (1)도 가능 -->컬럼순서
						String regDate = rs.getString("regDate");
						String updateDate = rs.getString("updateDate");
						String title = rs.getString("title");
						String body = rs.getString("body");

						Article article = new Article(id, regDate, updateDate, title, body);
						articles.add(article);
					}

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (rs != null && !rs.isClosed()) {
							rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
				System.out.println("결과 : " + articles);

				//
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println("id      |       제목");
				for (Article article : articles) {
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}

			} else if (cmd.startsWith("article modify ")) {
				int id = Integer.parseInt(cmd.split(" ")[2]);
				
				System.out.printf(" == %d번 게시물 수정 ==\n", id);

//				int id = lastArticleId + 1;

				System.out.print("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.print("수정할 내용 : ");
				String body = sc.nextLine();

				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");

					String sql = "UPDATE article ";
					sql += " SET updateDate = NOW()";
					sql += ", title = '" + title + "'";
					sql += ", `body` = '" + body + "'";
					sql += " WHERE id = '" + id + "';";

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
//				articles.add(article);
//				lastArticleId++;

				System.out.printf(" %d 번 글이 생성되었습니다.\n", id);
			}

		}
		sc.close();
	}
}
