<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.Participate" %>
<%@ page import= "beans.Project" %>
<%@ page import= "beans.Task" %>
<%@ page import= "beans.Handle" %>
<%@ page import="java.util.List" %>
<%
String name = (String)session.getAttribute("UserName");
String id =(String)session.getAttribute("UserId");
Project finded_project = (Project)request.getAttribute("finded_project");
int project_id = (int)request.getAttribute("prj_id");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
List<Task> task_list = (List<Task>) request.getAttribute("task_list");
List<Handle> handleList = (List<Handle>) request.getAttribute("handleList");
List<Participate> participateList = (List<Participate>) request.getAttribute("participateList");
List<User> userList = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <title>タスクの一覧</title>
  <link rel="stylesheet" type="text/css" href="css/showProject.css">
  <script src="https://kit.fontawesome.com/ab67e91441.js" crossorigin="anonymous"></script>
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

  <div class="prj0">
    <div class="prj-header">
      <div class="prj-header-left">
        <div class="prj-header-left-left">
          <% if(finded_project.getProjectSTATUS()==0) { %>
          <div class="status0">予定</div>
          <% } %>
          <% if(finded_project.getProjectSTATUS()==1) { %>
          <div class="status1">実行中</div>
          <% } %>
          <% if(finded_project.getProjectSTATUS()==2) { %>
          <div class="status2">終了</div>
          <% } %>
        </div>
        <div class="prj-header-left-right">
          <%if(finded_project.getIsDelayed()){%>
          遅れ
          <%}%>
          </div>
        </div>
        <div class="prj-header-right0">
          <%=finded_project.getDeadline()%>まで
        </div>
      </div><br>
      <div class="prj-title0">
        <%=finded_project.getProjectTITLE()%>
      </div>
      <div class="members0">
        <div class="members-title">
          プロジェクトホスト<br>
        </div>
        <%int q=0;%>
        <%for (Participate participate : participateList) {%>
          <%participate = participateList.get(q);%>
          <%if(project_id == participate.getPrjId()){%>
            <%if(participate.getIsPrjOwn() == true){%>
              <%for(int jjj=0;jjj<=userList.size()-1;jjj++){%>
                <%User user = userList.get(jjj);%>
                <%if(participate.getUserId().equals(user.getId())){%>
                  <%=user.getName()%>
                <%}%>
              <%}%>
            <%}%>
          <%}%>
        <%q++;}%>
        <div class="members-title">
          <br>参加者<br>
        </div>
        <%int j=0;%>
        <%for (Participate participate : participateList) {%>
          <%participate = participateList.get(j);%>
          <%if(project_id == participate.getPrjId()){%>
            <%for(int w1=0;w1<=userList.size()-1;w1++){%>
              <%User user = userList.get(w1);%>
              <%if(participate.getUserId().equals(user.getId())){%>
                <% if(name.equals(user.getName())) { %>
                  ●
                <% } else { %>
                  　
                <% } %>
                <%=user.getName()+" "%>
                <br>
              <%}%>
            <%}%>
          <%}%>
        <%j++;} %>
      </div>
      <div class="overview0">
        <div class="overview-title">
          概要<br>
        </div>
        <%=finded_project.getOverview()%>
      </div>
    </div>
    <c:url value="/UpdateProject" var="url2">
      <c:param name="id" value="<%=finded_project.getPrjIDStr()%>" />
    </c:url>
    <% if(finded_project.getHostID().equals(id)) { %>
    <a href="${url2}">編集</a>
    <% } %>
    <!-- <br> -->
    <%if(task_list != null) {%>
    <!-- タスク一覧<br> -->
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

        <c:url value="/UpdateTask" var="url4">
          <c:param name="id" value="<%=task.getTaskIdStr()%>" />
          <c:param name="prj_id" value="<%=finded_project.getPrjIDStr()%>" />
        </c:url>
        <a class="fas fa-pencil-alt edit" href="${url4}"></a>

        <div class="members">
          <div class="members-title">
            担当者<br>
          </div>
          <%int jj=0;%>
          <%for (Handle handle : handleList) {%>
          <%handle = handleList.get(jj);%>
          <%if(task.getTaskID() == handle.getTaskId()){%>
          <%int w1=0;%>
          <%for(User user : userList){%>
          <%user = userList.get(w1);%>
          <%if(handle.getUserId().equals(user.getId())){%>
          <% if(name.equals(user.getName())) { %>
          ●<%=user.getName()+" "%><br>
          <%}else{%>
          <%=user.getName()+" "%><br>
          <%}%>
              <%}%>
          <%w1++;}%>
          <%}%>
        <%jj++;} %>
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
        <br>
        <c:url value="/DeleteTask" var="url5">
          <c:param name="id" value="<%=task.getTaskIdStr()%>" />
        </c:url>
        <br><br><br><br><br><br><br><br><br>
        <a href="${url5}">タスクの削除</a>
      </div>
      <%i1 = i1+1;%>
      <%} }else{%>
      タスクがありません
      <%}%>
      <% if(finded_project.getProjectSTATUS()==2) { %>
        <% }else{ %>

      <div class="createTask">
        <c:url value="/CreateTask" var="url3" >
          <c:param name="id" value="<%=finded_project.getPrjIDStr()%>"/>
      </c:url>
      <a href="${url3}">＋</a>
    </div>
    <% } %>
</body>

</html>