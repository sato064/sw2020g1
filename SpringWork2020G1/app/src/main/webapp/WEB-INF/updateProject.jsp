<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="beans.Project" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%Project project = (Project)request.getAttribute("project"); %>
<%List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<%String name = (String)session.getAttribute("UserName");%>
<%String[] errorMessage = (String[])request.getAttribute("errorMessage");
request.setAttribute("Prj", project);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/index.css">
    </head>
    <body>
        <br>
        プロジェクト編集<br>
        <form action="./UpdateProject" name="formName" method="post">
            タイトル   <%if(!(errorMessage[0].equals("null"))){%>
                <font color="red"><%=errorMessage[0]%></font><%}%><br>
            <input type="text" id="title" name="title" value="<%=project.getProjectTITLE()%>" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required><br>
            概要   <%if(!(errorMessage[1].equals("null"))){%>
                <font color="red"><%=errorMessage[1]%></font><%}%><br>
            <textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview"  maxlength="300" size="80" pattern=".*\S+.*" required>
            <%=project.getOverview()%>
            </textarea><br>
            参加者<br>
            <select id="user" name="user" multiple>
                <option disabled selected>参加者を選択してください</option>
                <%for(int count=0;count<=userList.size()-1;count=count+1){%>
                    <%User use = userList.get(count);%>
                    <%if(name.equals(use.getName())){}else{%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                    <%}%>
                <%}%>
            </select><br>
            期日   <%if(!(errorMessage[2].equals("null"))){%>
                <font color="red"><%=errorMessage[2]%></font><%}%><br>
            <input type="date" id="deadline" name="deadline"><br>
            <div hidden>
            <input type="text" id="prjid" name="prjid" value="<%=project.getProjectID()%>">
            </div>
            <button type="submit" onclick="history.back()">戻る</button>
            <button type="submit">作成</button>
        </form>
        
    </body>
</html>