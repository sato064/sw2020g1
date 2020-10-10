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
    <link rel="stylesheet" type="text/css" href="css/index.css">
  </head>
  <body>
    ${sessionScope.UserName}さん
    ようこそ<br>
    <a href="./CreateProject">プロジェクト新規作成はこちら</a><br>
  </body>
</html>