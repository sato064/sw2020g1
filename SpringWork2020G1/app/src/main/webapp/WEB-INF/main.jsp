<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<%
String name = (String)session.getAttribute("UserName");
System.out.println(name);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>top</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
  </head>
  <body>
    <header>
      ようこそ
      ${sessionScope.UserName}さん
      <a href="./CreateProject">＋</a><br>
    </header>
  </body>
</html>