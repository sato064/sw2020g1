use mst_todo;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS participates;
DROP TABLE IF EXISTS handles;

CREATE TABLE users (
  id VARCHAR(16) PRIMARY KEY NOT NULL,
  name VARCHAR(20) NOT NULL,
  password VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE projects (
  id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title VARCHAR(30) NOT NULL,
  overview VARCHAR(300) NOT NULL,
  host_id VARCHAR(16) NOT NULL,
  deadline datetime NOT NULL,
  status INT(1) NOT NULL,
  is_delayed boolean NOT NULL DEFAULT false,
  FOREIGN KEY(host_id) REFERENCES users(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE tasks (
  id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  prj_id INT(10) NOT NULL,
  title VARCHAR(30) NOT NULL,
  overview VARCHAR(400) NOT NULL,
  deadline datetime NOT NULL,
  status INT(1) NOT NULL,
  is_delayed boolean NOT NULL DEFAULT false,
  FOREIGN KEY(prj_id) REFERENCES projects(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE participates (
  user_id VARCHAR(16) NOT NULL,
  prj_id INT(10) NOT NULL,
  is_prj_own boolean NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE handles (
  task_id INT(10) NOT NULL,
  prj_id INT(10) NOT NULL,
  user_id VARCHAR(16) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


INSERT INTO users
VALUES ('a181408w', '近藤羽音', '$2a$10$XOJmkMkhALPxnM0FAedt0OE7X66y1CMCyyu5WzTEJYvNNqHsaf/8a'),
('e185401f', '市川晴也', '$2a$10$ACbEBSxsLHETB6Ey4vMpKeZbHN4HCzBnv5fRBiZjjek9pzb.xfPhC'),
('e185408w', '佐藤央', '$2a$10$LqJY0WohA8ktos.9ms4YM.RdZIO.A3aAlfHuiRc/4LmR0YsazsQUe'),
('e185417m', '渡邊幹斗', '$2a$10$zA/VW.M6g7b.88X0WqktB.oVlBY5xXZKpZZfQNJIUoode5HdJI4Qa');

-- hanehane,seiyaseiya,oooooooo,kantokanto

INSERT INTO projects (title, overview, host_id, deadline, status, is_delayed)
VALUES ('親睦会', 'これからともにアプリ開発していくので懇親会をしましょう！', 'a181408w', '2020-04-01 00:00:00', 2, true),
('2020年G1春課題', '研究室で使う共有ToDoリストのアプリ開発', 'e185401f', '2020-10-16 00:00:00', 1, false);

INSERT INTO tasks (prj_id, title, overview, deadline, status, is_delayed)
VALUES (2, '要求仕様書', 'インスペクション回数は５回', '2020-06-07 10:30:00', 2, false),
(2,'画面シナリオ', 'インスペクション回数は3回', '2020-06-22 10:00:00', 2, false),
(2, 'クラス図', 'インスペクション回数は3回', '2020-07-06 09:25:00', 2, false),
(2, 'DB設計書', 'インスペクション回数は3回', '2020-07-13 12:00:00', 2, false),
(2, 'シーケンス図', 'インスペクション回数は1人3回', '2020-07-20 14:30:00', 1, true),
(2, '実装', '未定', '2020-10-16 00:00:00', 1, false);

INSERT INTO participates
VALUES ('a181408w', 1, true),
('a181408w', 2, false),
('e185401f', 1, false),
('e185401f', 2, true),
('e185408w', 1, false),
('e185408w', 2, false),
('e185417m', 1, false),
('e185417m', 2, false);

INSERT INTO handles
VALUES (1, 2, 'e185417m'),
(2, 2, 'a181408w'),
(3, 2, 'e185408w'),
(4, 2, 'e185401f');