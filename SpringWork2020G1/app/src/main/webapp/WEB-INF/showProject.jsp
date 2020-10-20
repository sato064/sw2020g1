<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Project" %>
<%@ page import= "beans.Task" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
String id =(String)session.getAttribute("UserId");
Project finded_project = (Project)request.getAttribute("finded_project");
int project_id = (int)request.getAttribute("prj_id");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
List<Task> task_list = (List<Task>) request.getAttribute("task_list");
List<Participate> participateList = (List<Participate>) request.getAttribute("participateList");
List<User> userList = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>タスクの一覧</title>
    <link rel="stylesheet" type="text/css" href="css/showProject.css">
  </head>
  <body>
    <header>
      ようこそ
      <div class="header-left">
        <div class="header-left-top">
          ようこそ　${sessionScope.UserName}さん
        </div>
        <div class="header-left-bottom">
          <div class="header-left-left">
            <a href="/SpringWork2020G1/Logout">ログアウト </a>
          </div>
          <div class="header-left-right">
            <a href="./UpdateUser">ユーザ情報の変更</a>
          </div>
        </div>
      </div>
      <div class="header-right">
        <div class="header-right-left">
          <a href="./Main">プロジェクト一覧</a>
        </div>
        <div class="header-right-right">
          <a href="./CreateProject">＋</a>
        </div>
      </div>
    </header><br><br><br><br><br>

    プロジェクト名:<%=finded_project.getProjectTITLE()%><br>
    詳細:<%=finded_project.getOverview()%><br>
    期日:<%=finded_project.getDeadline()%><br>
    <c:url value="/JoinProject" var="url" >
　           <c:param name="id" value="<%=finded_project.getPrjIDStr()%>"/>
            </c:url>
            <% if(finded_project.getProjectSTATUS()==2) {}else{ %>
              <a href="${url}">このプロジェクトに参加する</a>
            <% } %>
    <c:url value="/UpdateProject" var="url2" >
    <c:param name="id" value="<%=finded_project.getPrjIDStr()%>"/>
    </c:url>
    <% if(finded_project.getHostID().equals(id)) { %>
      <a href="${url2}">編集</a>
    <% } %>
    <br>
    <c:url value="/CreateTask" var="url3" >
    <c:param name="id" value="<%=finded_project.getPrjIDStr()%>"/>
    </c:url>
    <a href="${url3}">タスクの新規作成</a>
    <%if(task_list != null) {%>
    タスク一覧<br>
    <%int i1 = 0;%>
    <%for (Task task : task_list) { %>
      <%int k1 = 0;%>
      <%boolean noParticipate = true; %>
      <%task = task_list.get(i1);%>
      <div class="prj">
        <div class="prj-header">
          <div class="prj-header-left">
            <div class="prj-header-left-left">
              <% if(task.getTaskSTATUS()==0) { %>
                <div class="status0">予定</div>
              <% } %>
              <% if(task.getTaskSTATUS()==1) { %>
                <div class="status1">実行中</div>
              <% } %>
              <% if(task.getTaskSTATUS()==2) { %>
                <div class="status2">終了</div>
              <% } %>
            </div>
            <div class="prj-header-left-right">
              <%if(task.getIsDelayed()){%>
                遅れ
              <%}%>
            </div>
          </div>
          <div class="prj-header-right">
            <%=task.getDeadline()%>まで
          </div>
        </div><br>
        <div class="prj-title">
          <%=task.getTaskTITLE()%>
        </div>

        <div class="view">
          <div class="overview">
            <div class="overview-title">
              概要<br>
            </div>
            <%=task.getOverview()%>
          </div>
          <div class="participate">
            
          </div>
        </div>
      </div>
      <%i1 = i1+1;%>
    <%} }else{%>
      タスクがありません
    <%}%><br>




    <a href="./Main">ホームへ戻る</a><br>
  </body>
</html>