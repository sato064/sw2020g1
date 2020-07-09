# 櫨山研2020年度春課題G1 データベース設計図

## データベース名　mst_todo

### テーブル一覧
user

|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|name|string|30|NO|NO|なし|ユーザ名||
|2|loginid|string|20|YES|NO|なし|ログイン時のid||
|3|password|string|20|NO|NO|なし|パスワード|ハッシュ化する|

project
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|なし|プロジェクトid||
|2|title|string|30|NO|NO|なし|プロジェクトタイトル||
|3|overview|string|300|NO|NO|なし|プロジェクトの概要||
|4|host_id|string|20|NO|NO|なし|プロジェクトホストのloginid|user/loginidの外部キー|
|5|deadline|datetime||NO|NO|なし|プロジェクトの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|プロジェクトの状態||
|7|isDelayed|boolean||NO|NO|なし|遅れ状況|プロジェクトが遅れていればtrue|

Task
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|id|int|10|YES|NO|なし|タスクid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトid||
|3|title|string|30|NO|NO|なし|タスクタイトル||
|4|overview|string|400|NO|NO|なし|タスクの概要||
|5|deadline|datetime||NO|NO|なし|タスクの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|タスクの状態||
|7|isDelayed|boolean||NO|NO|なし|遅れ状況|プロジェクトが遅れていればtrue|

Participate
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|user_loginid|string|16|YES|NO|他idとの重複不可|ユーザid||
|2|prj_id|int|10|NO|NO|なし|プロジェクトタイトル||
|3|isPrjOwn|boolean||NO|NO|なし|ホストが自分かどうか|自分がホストならtrue|

Handle
|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|---|---|---|---|---|---|---|---|---|
|1|task_id|int|10|YES|NO|なし|タスクid|prj_idとの複合キー|
|2|prj_id|int|10|YES|NO|なし|プロジェクトid|task_idとの複合キー|
|3|user_loginid|string|16|NO|NO|なし|概要||
