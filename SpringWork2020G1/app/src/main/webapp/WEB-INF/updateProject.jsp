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
        <title>プロジェクトの編集（ホストのみ）</title>
        <link rel="stylesheet" type="text/css" href="css/updateProject.css">
    </head>
    <body>
        <header>
            <div class="header-left">
                ようこそ　${sessionScope.UserName}さん
            </div>
            <div class="header-right">
                <a href="./Main">プロジェクト一覧</a>
            </div>
        </header>
        <div class="h1">
            プロジェクト編集
        </div>
        <form action="./UpdateProject" name="formName" method="post">
            <div class="tag">
                タイトル
            </div>
            <input type="text" id="title" name="title" value="<%=project.getProjectTITLE()%>" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required><br>
            <div class="tag">
                概要
            </div>
            <textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview"  maxlength="300" size="80" pattern=".*\S+.*" required><%=project.getOverview()%></textarea><br>
            <div class="tag">
                参加者
            </div>
            <select id="user" name="user" multiple>
                <option disabled selected>参加者を選択してください</option>
                <%for(int count=0;count<=userList.size()-1;count=count+1){%>
                    <%User use = userList.get(count);%>
                    <%if(name.equals(use.getName())){}else{%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                    <%}%>
                <%}%>
            </select><br>
            <div class="tag">
                プロジェクトホストの変更
            </div>
            <select id="hostID" name="hostID">
                <%for(int count=0;count<=userList.size()-1;count=count+1){%>
                    <%User use = userList.get(count);%>
                    <%if(name.equals(use.getName())){%>
                        <option value="nochange" selected><%=use.getName()%></option>
                    <%}else{%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                    <%}%>
                <%}%>
            </select><br>
            <div class="tag">
                状態
            </div>
            <div class="radio">
                <div class="status0">
                    <input type="radio" name="status" value="0">予定</label>
                </div>
                <div class="status1">
                    <input type="radio" name="status" checked="checked" value="1">実行中</label>
                </div>
                <div class="status2">
                    <input type="radio" name="status" value="2">終了</label>
                </div>
            </div>
            <div class="tag">
                期日   
            </div>
            <%if(!(errorMessage[0].equals("null"))){%>
            <font color="red"><%=errorMessage[0]%></font><%}%><br>
            <input type="date" id="deadline" name="deadline"><br>
            <div hidden>
            <input type="text" id="prjid" name="prjid" value="<%=project.getProjectID()%>">
            </div><br>
            <div submit>
                <div class="green1">
                    <button type="button" onclick="history.back()">戻る</button>
                </div>
                <div class="green2">
                    <button type="submit">更新</button>
                </div>
                <div class="delete">
                    <button type="submit" name="delete">削除</button>
                </div>
            </div>
        </form>
        
    </body>
</html>