<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/registUser.css">
  </head>
  <body>
    <form action="./RegistUser" method="post">
      <input type="text" placeholder="メールアドレス" name="mail_address" required/><br>
      <input type="text" placeholder="名前" name="name" required/><br>
      <input type="password" placeholder="パスワード" name="password" required/><br>
      <input type="submit" value="新規登録する" name="OK" /><br>
    </form>
  </body>
</html>
