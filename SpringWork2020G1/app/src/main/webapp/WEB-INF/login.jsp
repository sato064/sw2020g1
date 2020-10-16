<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>ログイン画面</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
  </head>
  <body>
    <header>
      ようこそ
    </header>
    <div class="h1">
      ログイン画面
    </div>
    <div class="h2">
      登録したユーザIDとパスワードを入力してください。
    </div>
    <form action="./Login" method="post">
      <div class="tag">
        ユーザ<br>
      </div>
      <input type="text" placeholder="ユーザID" name="loginid" required/><br>
      <div class="tag">
        パスワード<br>
      </div>
      <input type="password" placeholder="パスワード" name="password" required/><br>
      <a href="./RegistUser">新規ユーザ登録</a>
      <input type="submit" value="　 ログイン 　" name="OK" /><br>
    </form>
  </body>
</html>