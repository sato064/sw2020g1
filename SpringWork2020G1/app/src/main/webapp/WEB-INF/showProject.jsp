<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Task" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
Task finded_task = (Task)request.getAttribute("finded_task");
List<Task> taskList = (List<Task>) request.getAttribute("taskList");
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
      <a href="./CreateTask">＋</a><br>
      <br>
    </header>
    タスク名:<%=finded_task.getTaskTITLE()%><br>
    詳細:<%=finded_task.getOverview()%><br>
    期日:<%=finded_task.getDeadline()%><br>
    <table border="1">
      <center>
      <tr>
        <th>タスク名</th>
        <th>概要</th>
        <th>期日</th>
        <th>担当者</th>
        <th>スケジュール状況</th>
        <th>詳細</th>
        
      </tr>
      <%int i = 0;%>
      <%for (Task task: taskList) {%>
        <%int k = 0;%>
        <%task = taskList.get(i);%>
        <tr>
          <td><%=task.getTaskTITLE()%></td>
          <td><%=task.getOverview()%></td>
          <td><%=task.getDeadline()%></td>
          <td>
            <%for (Participate participate : participateList) {%>
              <%participate = participateList.get(k);%>
              <%if(task.getTaskID() == participate.getPrjId()){%>
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
              <%if(task.getIsDelayed()){%>
                遅れ発生
              <%}%>
                
           </td>
           <td>
            <c:url value="/ShowTask" var="url" >
　           <c:param name="id" value="<%=task.getTskIDStr()%>"/>
           </c:url>
           <a href="${url}">リンク</a>
          </td>
        </tr>
        <%i = i+1;%>
      <%}%>
      </center>
    </table>
    <br><br>

   
    <a href="./Main">ホームへ戻る</a><br>


    
</html>