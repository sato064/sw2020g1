<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Project" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
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
    <table border="1">
      <center>
      <tr>
        <th>プロジェクト名</th>
        <th>概要</th>
        <th>期日</th>
        <th>メンバー</th>
        <th>詳細</th>
      </tr>
      <%int i = 0;%>
      <%for (Project project : projectList) {%>
        <%int k = 0;%>
        <%int w = 0;%>
        <%project = projectList.get(i);%>
        <tr>
          <td><%=project.getProjectTITLE()%></td>
          <td><%=project.getOverview()%></td>
          <td><%=project.getDeadline()%></td>
          <td>
            <c:url value="/Main" var="url" >
　           <c:param name="id" value="<%=project.getPrjIDStr()%>"/>
            </c:url>
            <a href="${url}">リンク</a>
          </td>
        </tr>
        <%i = i+1;%>
      <%}%>
      </center>
    </table>
  </body>
</html>