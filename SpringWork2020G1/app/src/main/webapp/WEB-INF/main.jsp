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
      <div class="welcome">
        ようこそ
        ${sessionScope.UserName}さん
      </div><br>
      <div class="logout">
        <a href="/SpringWork2020G1/Logout">ログアウト </a>
      </div>
        <div class="right">
          <a href="./CreateProject">＋</a>
        </div>
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
        <%project = projectList.get(i);%>
        <tr>
          <td><%=project.getProjectTITLE()%></td>
          <td><%=project.getOverview()%></td>
          <td><%=project.getDeadline()%></td>
          <td>
            <%for (Participate participate : participateList) {%>
              <%participate = participateList.get(k);%>
              <%if(project.getProjectID() == participate.getPrjId()){%>
                <%for(int w=0;w<=userList.size()-1;w++){%>
                  <%User user = userList.get(w);%>
                  <%if(participate.getUserId().equals(user.getId())){%>
                    <%=user.getName()+" "%>
                  <%}%>
                <%}%>
              <%}%>
            <%k++;} %>
           </td>
           <td>
             <c:url value="/ShowProject" var="url" >
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