<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/registUser.css">
  </head>
  <body>
    ようこそ
    ユーザ登録
    <form action="./RegistUser" method="post">
      氏名
      <input type="text" placeholder="全角半角1文字以上20文字以下" name="name" required/><br>
      ユーザID
      <input type="text" placeholder="半角英数字1〜16文字" name="name" required/><br>
      パスワード
      <input type="password" placeholder="半角英数字1〜16文字" name="password" required/><br>
      パスワード確認用
      <input type="password" placeholder="半角英数字1〜16文字" name="password" required/><br>
      <input type="submit" value="登録する" name="OK" /><br>
    </form>
    <a href="/index.jsp">戻る</a>

    <!-- <form action="./RegistUser" method="post">
      <input type="text" placeholder="メールアドレス" name="mail_address" required/><br>
      <input type="text" placeholder="名前" name="name" required/><br>
      <input type="password" placeholder="パスワード" name="password" required/><br>
      <input type="submit" value="新規登録する" name="OK" /><br>
    </form> -->

  </body>
</html>
