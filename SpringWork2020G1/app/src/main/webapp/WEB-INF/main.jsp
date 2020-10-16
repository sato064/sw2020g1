<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Project" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
List<Project> list = (List<Project>) request.getAttribute("ProjectList");
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
      <br>
    </header>
      <table border="1">
        <center>
        <tr>
          <th>プロジェクト名</th>
          <th>概要</th>
          <th>期日</th>

        </tr>
      <c:forEach var="Project" items="${ProjectList}">
        <tr>
          <td>${Project.projectTITLE}</td>
          <td>${Project.overview}</td>
          <td>${Project.deadline}
          </td>
        </tr>
      </c:forEach>
      </table>
    </center>
    
  </body>
</html>