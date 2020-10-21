<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<%String name = (String)session.getAttribute("UserName");%>
<%String prj_id = (String)request.getAttribute("PrjId");%>
<%String today = (String)request.getAttribute("today");%>
<%String[] errorMessage = (String[])request.getAttribute("errorMessage");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>タスクの作成(参加者のみ)</title>
        <link rel="stylesheet" type="text/css" href="css/CreateTask.css">
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
            新規タスク作成
                <font size="small">
                    　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
                    作成したタスクは【予定】になります。編集から状態を変更してください。
                </font>
        </div>
        <form action="/SpringWork2020G1/CreateTask" method="post">
            <div class="tag">
                タイトル
            </div>
            <input type="text" id="title" name="title" placeholder="30文字以内で記載してください" maxlength="30" minlength="1" pattern=".*\S+.*" size="35" required>
            <div class="tag">
                概要
            </div>
            <textarea class="uk-textarea" rows="4" type="overview" id="overview" name="overview" placeholder="400文字以内で記載してください" maxlength="400" size="80" pattern=".*\S+.*" required></textarea>
            <div class="tag">
                担当者
            </div>
            <select id="user" name="user" multiple>
                <option disabled selected>担当者を選択してください</option>
                <%for(int count=0;count<=userList.size()-1;count=count+1){%>
                    <%User use = userList.get(count);%>
                        <option value="<%=use.getId()%>"><%=use.getName()%></option>
                <%}%>
            </select>
            <div class="tag">
                期日
            </div>
            <input type="date" id="deadline" name="deadline" value="<%=today%>"><br>
            <div hidden>
            <input type="text" id="prj_id" name="prj_id" value="<%=prj_id%>">
            </div>
            <input type="submit" value="戻る" onclick="history.back()">
            <input type="submit" value="作成" />
        </form>
    </body>
</html>