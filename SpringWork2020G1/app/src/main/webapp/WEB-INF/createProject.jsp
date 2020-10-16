<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<%String name = (String)session.getAttribute("UserName");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/index.css">
    </head>
    <body>
        ようこそ<br>
        新規プロジェクト作成<br>
        <form action="/SpringWork2020G1/CreateProject" name="formName" method="post">
            タイトル<input type="text" id="title" name="title" placeholder="プロジェクト名" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required><br>
            概要<textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview" placeholder="概要" maxlength="300" size="80" pattern=".*\S+.*" required></textarea><br>
            <select id="user" name="user" multiple>
                <option disabled selected>参加者を選択してください</option>
                <%for(int count=0;count<=userList.size()-1;count=count+2){%>
                    <%User use = userList.get(count);%>
                    <%if(name.equals(use.getName())){}else{%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                    <%}%>
                <%}%>
            </select><br>
            期日<input type="date" id="deadline" name="deadline" value="2020-10-10"><br>
            <button type="submit" onclick="history.back()">戻る</button>
            <button type="submit">作成</button>
        </form>
        
    </body>
</html>