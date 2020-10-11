<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%ArrayList<String> userList = (ArrayList<String>) request.getAttribute("userList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/index.css">
    </head>
    <body>
        ようこそ<br>
        新規プロジェクト作成<br>
        <form action="/SpringWork2020G1/CreateProject" method="post">
            タイトル<input type="text" id="title" name="title" placeholder="プロジェクト名" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required><br>
            概要<textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview" placeholder="概要" maxlength="300" size="80" pattern=".*\S+.*" required></textarea><br>
            担当者<select name="user" size="5" multiple>
            <%int i = 0;%>
            <%while(i<=4){ %>
            <%String use = "渡邊";  //userList.get(i)%>
            <option value="<%=i%>"><%=use+i%></option>
            <%i=i+1; } %>
            </select><br>
            期日<input type="date" id="deadline" name="deadline" value="2020-10-10"><br>
            <input type="submit" value="戻る" onclick="history.back()">
            <input type="submit" value="作成" />
        </form>
    </body>
</html>