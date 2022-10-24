DROP DATABASE IF EXISTS jdbc_article_manager;

CREATE DATABASE jdbc_article_manager;

USE jdbc_article_manager;

CREATE TABLE article(
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title VARCHAR(200) NOT NULL,
	`body` TEXT NOT NULL,
	memberId INT UNSIGNED NOT NULL 
);

SELECT * FROM article;

DESC article;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목',RAND()),
`body` = CONCAT('내용',RAND());

# member 테이블 생성

CREATE TABLE `member`(
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginID VARCHAR(20) NOT NULL,
	loginPw VARCHAR(50)  NOT NULL,
	`name` VARCHAR(50) NOT NULL
);

# member data 추가
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginID = CONCAT('testId',RAND()),
loginPw = CONCAT('testPw',RAND()),
`name` = CONCAT('testName',RAND());

DESC `member`;

SELECT * FROM `member`;

SELECT COUNT(loginId) > 0
FROM `member`
WHERE loginId = 'test';


SELECT A.*, M.name AS writerName
FROM  article AS A
JOIN `member` AS M
ON M.id = A.memberId
WHERE A.memberId =1;



