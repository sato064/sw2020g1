<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Studentをimportする -->
<%@ page import= "beans.User" %>
<% User User = (User)request.getAttribute("User");
  String username = (String)request.getAttribute("username");
  String name = (String)session.getAttribute("UserName");
  System.out.println(name);

%>



<html>
  <head>
    <title>BunG-メインページ</title>
  </head>

  <body bgcolor="skyblue">
    <br />
    ようこそ ${sessionScope.UserName} さん<br>

    <a href="/BunG/RegistInfo">店舗情報の新規登録 </a><br />
    <a href="/BunG/SearchInfo">店舗情報の検索 </a><br />
    <a href="/BunG/DeleteInfo"> 店舗情報の削除</a><br />
    <form action="./ShowFavStore" method="post">
      <input type="submit" name="OK" value="お気に入り店舗の閲覧"/>
    </form>
    <br />
    <form action="./Logout" method="post">
      <input type="submit" name="OK" value="ログアウト"/>




    <br />
  </body>
</html>
