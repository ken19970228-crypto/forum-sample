INSERT IGNORE INTO users (user_id, email, nickname, password, authority, date)
VALUES
	(1, 'admin@test.com', 'adminUser', 'test_password', 'admin', '2025-08-28 00:00:00'),
	(2, 'taira@test.com', 'testTaira', 'YWJjZGUxMjM0NQ==', 'user', '2025-08-28 00:00:00');

INSERT IGNORE INTO articles (article_id, user_id, user_nickname, title, text, date)
VALUES
	(1, 2, 'testTaira', 'タイトルテスト1', '本文テスト1', '2025-08-28 17:22:38'),
	(2, 2, 'testTaira', 'タイトルテスト2', '本文テスト2', '2025-08-28 18:22:38'),
	(3, 2, 'testTaira', 'タイトルテスト3', '本文テスト3', '2025-08-28 19:22:38');