<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Project" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");

%>
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
      ユーザ情報更新
    </div>
    <form action="./UpdateUser" method="post">
      <div class="tag">
        氏名<br>
      </div>
        <input type="text" value="${sessionScope.UserName}" name="name" required/><br>
      <div class="tag">
        旧パスワード<br>
      </div>
      <input type="password" placeholder="　半角英数字1〜16文字" name="old_password" required/><br>
      <div class="tag">
        新パスワード<br>
      </div>
      <input type="password" placeholder="　半角英数字1〜16文字" name="new_password" required/><br>
      <div class="tag">
        新パスワード(確認用)<br>
      </div>
      <input type="password" placeholder="　半角英数字1〜16文字" name="new_password_con" required/><br>
      <a href="./Main">　戻る　</a>
      <input type="submit" value="登録する" name="OK" />
    </form>
  </body>
</html>
