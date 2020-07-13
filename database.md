# 櫨山研2020年度春課題G1 データベース設計図

## 櫨山研究室春課題G1メンバー

◎市川晴也
近藤羽音
佐藤央
渡邊幹斗

## データベース名　mst_todo
</br>

## 改版履歴
***
|発行日|版数|改版内容|
|-------|----|------------------|
|2020/07/09|1.0|初版|
|2020/07/13|2.0|・改版履歴やG1メンバーの記述を追加<br>・login_idをidに変更し、Userテーブルのカラムの順序を変更<br>・Userテーブルのidの長さを16に変更<br>・テーブル名の変更<br>・Taskテーブルにおける外部キーの記述を追加<br>・パスワードの型を変更、ハッシュ化の詳細を記述|
### テーブル一覧
user

|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|string|16|YES|NO|なし|ログイン時のid||
|2|name|string|30|NO|NO|なし|ユーザ名||
|3|password|varchar|255|NO|NO|なし|パスワード|BCryptを使用し、ハッシュ化したもの|

project
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|なし|プロジェクトid||
|2|titles|string|30|NO|NO|なし|プロジェクトタイトル||
|3|overview|string|300|NO|NO|なし|プロジェクトの概要||
|4|host_id|string|16|NO|NO|user/idの外部キー|プロジェクトホストのid||
|5|deadline|datetime||NO|NO|なし|プロジェクトの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|プロジェクトの状態||
|7|is_delayed|boolean||NO|NO|デフォルト:false|遅れ状況|プロジェクトが遅れていればtrue|

Task
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|なし|タスクid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid|project/idの外部キー|
|3|titles|string|30|NO|NO|なし|タスクタイトル||
|4|overview|string|400|NO|NO|なし|タスクの概要||
|5|deadline|datetime||NO|NO|なし|タスクの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|タスクの状態||
|7|is_delayed|boolean||NO|NO|なし|デフォルト:false|プロジェクトが遅れていればtrue|

Participate
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|user_id|string|16|NO|NO|他idとの重複不可|ユーザid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid||
|3|is_prj_own|boolean||NO|NO|なし|ホストが自分かどうか|自分がホストならtrue|

Handle
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|task_id|int|10|NO|NO|なし|タスクid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid||
|3|user_id|string|16|NO|NO|なし|他idとの重複不可|ユーザid|
