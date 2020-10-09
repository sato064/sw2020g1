<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/index.css">
  </head>
  <body>
    ようこそ<br>
    ログイン画面<br>
    登録したユーザIDとパスワードを入力してください。<br>
    <form action="./Login" method="post">
        <input type="text" placeholder="ユーザID" name="loginid" required/><br>
        <input type="password" placeholder="パスワード" name="password" required/><br>
        <input type="submit" value="ログイン" name="OK" /><br>
    </form> -->
    <a href="./RegistUser">新規ユーザ登録</a>
  </body>
</html>