<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/registRestaurant.css">
  </head>
  <body>
    <form action="./RegistRestaurant" method="post">
      <input type="text" placeholder="店名" name="name" required/><br>
      <input type="submit" value="飲食店の登録" name="OK" /><br>
      <a href="/Gourmet/Login">戻る</a>
    </form>
  </body>
</html>
