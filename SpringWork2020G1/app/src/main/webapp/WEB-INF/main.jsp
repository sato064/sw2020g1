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
          <a href="./FinishedPrj">終了したプロジェクト</a>
        </div>
        <div class="header-right-right">
            <a href="./CreateProject">＋</a>
        </div>
      </div>
    </header><br><br><br><br><br>

    <!-- ⭐️　⬇️表形式のやつは今後の参考になるからまだ残しておいてほしい⬇️　⭐️ -->
    <table border="1">
      <center>
      <tr>
        <th>プロジェクト名</th>
        <th>概要</th>
        <th>期日</th>
        <th>メンバー</th>
        <th>スケジュール状況</th>
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
              <%if(project.getIsDelayed()){%>
                遅れ発生
              <%}%>
                
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
    <br><br>

    <!-- ⭐️ここにソートのプルダウンメニュー追加してえ⭐️ -->

    <%int i1 = 0;%>
      <%for (Project project : projectList) {%>
        <%int k1 = 0;%>
        <%boolean noParticipate = true; %>
        <%project = projectList.get(i1);%>
        <div class="prj">
          <div class="prj-header">
            <div class="prj-header-left">
              <div class="prj-header-left-left">
                <% if(project.getProjectSTATUS()==0) { %>
                  <div class="status0">予定</div>
                <% } %>
                <% if(project.getProjectSTATUS()==1) { %>
                  <div class="status1">実行中</div>
                <% } %>
                <% if(project.getProjectSTATUS()==2) { %>
                  <div class="status2">終了</div>
                <% } %>
              </div>
              <div class="prj-header-left-right">
                <%if(project.getIsDelayed()){%>
                  遅れ
                <%}%>
              </div>
            </div>
            <div class="prj-header-right">
              <%=project.getDeadline()%>まで
            </div>
          </div><br>
          <div class="prj-title">
            <%=project.getProjectTITLE()%>
          </div>
          <div class="members">
            <div class="members-title">
              プロジェクトホスト<br>
            </div>
            <div class="members-title">
              参加者<br>
            </div>
            <%for (Participate participate : participateList) {%>
              <%participate = participateList.get(k1);%>
              <%if(project.getProjectID() == participate.getPrjId()){%>
                <%for(int w1=0;w1<=userList.size()-1;w1++){%>
                  <%User user = userList.get(w1);%>
                  <%if(participate.getUserId().equals(user.getId())){%>
                    <% if(name==user.getName()) { %>
                      <% noParticipate = false; %>
                      ●
                    <% } %>
                    <%=user.getName()+" "%>
                    <br>
                  <%}%>
                <%}%>
              <%}%>
            <%k1++;} %>
          </div>
          <div class="view">
            <div class="overview">
              <div class="overview-title">
                概要<br>
              </div>
              <%=project.getOverview()%>
            </div>
            <div class="participate">
              <!-- ⭐️ここに参加のボタンいれてえ⭐️ -->
              <% if(noParticipate == true) { %>
                <a href="${url}">このプロジェクトに参加する</a>
              <% } %>
            </div>
          </div>
        </div>
        <%i1 = i1+1;%>
      <%}%>
  </body>
</html>
