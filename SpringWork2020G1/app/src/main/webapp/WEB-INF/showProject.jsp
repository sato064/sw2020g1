<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Project" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
Project finded_project = (Project)request.getAttribute("finded_project");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
List<Participate> participateList = (List<Participate>) request.getAttribute("participateList");
List<User> userList = (List<User>) request.getAttribute("userList");
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
    プロジェクト名:<%=finded_project.getProjectTITLE()%><br>
    詳細:<%=finded_project.getOverview()%><br>
    期日:<%=finded_project.getDeadline()%><br>
    <c:url value="/JoinProject" var="url" >
　           <c:param name="id" value="<%=finded_project.getPrjIDStr()%>"/>
            </c:url>
            <a href="${url}">このプロジェクトに参加する</a>

    <a href="./Main">ホームへ戻る</a><br>


    
</html>