# 予定及び実行中のプロジェクトの閲覧

![sequence_prj_read](https://user-images.githubusercontent.com/63034711/88255487-70cfb080-ccf3-11ea-936f-bee7e9abf840.png)

諸注意
|||
|:--|:--|
|プロジェクト情報の取得|SELECT * FROM projects WHERE status != 2 ORDER BY deadline ASC;|
|プロジェクト参加情報の取得|SELECT * FROM participates INNER JOIN users ON participates.user_id = users.id;|
