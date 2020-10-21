<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="beans.Task" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<%String name = (String)session.getAttribute("UserName");%>
<%String prj_id = (String)request.getAttribute("prj_id");%>
<%String today = (String)request.getAttribute("today");%>
<%String[] errorMessage = (String[])request.getAttribute("errorMessage");%>
<%Task task = (Task)request.getAttribute("task");%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        
    </head>
    <body>
        ようこそ<br>
        タスク更新<br><br>

        <form action="/SpringWork2020G1/UpdateTask" method="post">
            タイトル<input type="text" id="title" name="title" value="<%=task.getTaskTITLE()%>" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required><br><br>
            概要<textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview"  maxlength="300" size="80" pattern=".*\S+.*" required><%=task.getOverview()%></textarea><br><br>
            <select id="user" name="user" multiple>
                <option disabled selected>担当者を選択してください</option>
                <%for(int count=0;count<=userList.size()-1;count=count+1){%>
                    <%User use = userList.get(count);%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                <%}%>
            </select><br><br>
            期日<input type="date" id="deadline" name="deadline" value="<%=task.getDeadline()%>"><br><br>

            状態<br>
            <input type="radio" name="status" value="0">予定
            <input type="radio" name="status" checked="checked" value="1">実行中
            <input type="radio" name="status" value="2">終了
            <br><br>
            <div hidden>
                <input type="text" id="task_id" name="task_id" value="<%=task.getTaskID()%>" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required>
                <input type="text" id="prj_id" name="prj_id" value="<%=prj_id%>" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required>


            </div>

            <a href="/SpringWork2020G1/Main">ホームに戻る</a><br><br>
            <input type="submit" value="更新" />
        </form>
    </body>
</html>