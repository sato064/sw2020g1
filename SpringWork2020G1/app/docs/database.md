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
|2020/07/13|2.0|・改版履歴やG1メンバーの記述を追加<br>・login_idをidに変更し、userテーブルのカラムの順序を変更<br>・userテーブルのidの長さを16に変更<br>・テーブル名の変更<br>・taskテーブルにおける外部キーの記述を追加<br>・パスワードの型を変更、ハッシュ化の詳細を記述<br>・is_delayedのデフォルト設定を変更
<br>|
|2020/07/18|3.0|・テーブル名を複数形に変更<br>・項目がずれていた欄を修正|
### テーブル一覧
users
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|string|16|YES|NO|なし|ログイン時のid||
|2|name|string|20|NO|NO|なし|ユーザ名||
|3|password|varchar|255|NO|NO|なし|パスワード|BCryptを使用し、ハッシュ化したもの|

projects
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|Autoincrement|プロジェクトid||
|2|title|string|30|NO|NO|なし|プロジェクトタイトル||
|3|overview|string|300|NO|NO|なし|プロジェクトの概要||
|4|host_id|string|16|NO|NO|user/idの外部キー|プロジェクトホストのid||
|5|deadline|datetime||NO|NO|なし|プロジェクトの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|プロジェクトの状態||
|7|is_delayed|boolean||NO|NO|デフォルト:false|遅れ状況|プロジェクトが遅れていればtrue|

tasks
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|Autoincrement|タスクid||
|2|prj_id|int|10|NO|NO|project/idの外部キー|プロジェクトid||
|3|title|string|30|NO|NO|なし|タスクタイトル||
|4|overview|string|400|NO|NO|なし|タスクの概要||
|5|deadline|datetime||NO|NO|なし|タスクの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|タスクの状態||
|7|is_delayed|boolean||NO|NO|デフォルト:false|遅れ状況|プロジェクトが遅れていればtrue|

participates
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|user_id|string|16|NO|NO|なし|ユーザid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid||
|3|is_prj_own|boolean||NO|NO|なし|ホストが自分かどうか|自分がホストならtrue|

handles
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|task_id|int|10|NO|NO|なし|タスクid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid||
|3|user_id|string|16|NO|NO|なし|ユーザid||