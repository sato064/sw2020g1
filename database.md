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
|2|title|string|30|NO|NO|なし|タイトル||
|3|overview|string|400|NO|NO|なし|概要||
|4|host_id|string|20|NO|NO|なし|プロジェクトホストのloginid|user/loginidの外部キー|
|5|deadline|datetime||NO|NO|なし|プロジェクトの期限||
|6|status|int|1|NO|NO|0,1,2のみ許可|プロジェクトの状態||
|7|isDelayed|boolean||NO|NO|なし|遅れ状況|プロジェクトが遅れていればtrue|