<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>ユーザ登録画面</title>
    <link rel="stylesheet" type="text/css" href="css/registUser.css">
  </head>
  <body>
    <header>
      ようこそ
    </header>
    <div class="h1">
      ユーザ登録
    </div>
    <form action="./RegistUser" method="post">
      <div class="tag">
        氏名<br>
      </div>
        <input type="text" placeholder="　全角半角1文字以上20文字以下" name="name" required/><br>
      <div class="tag">
        ユーザID<br>
      </div>
      <input type="text" placeholder="　半角英数字1〜16文字" name="id" required/><br>
      <div class="tag">
        パスワード<br>
      </div>
      <input type="password" placeholder="　半角英数字1〜16文字" name="password" required/><br>
      <div class="tag">
        パスワード確認用<br>
      </div>
      <input type="password" placeholder="　半角英数字1〜16文字" name="password" required/><br>
      <a href="./index.jsp">　戻る　</a>
      <input type="submit" value="登録する" name="OK" />
    </form>
  </body>
</html>
