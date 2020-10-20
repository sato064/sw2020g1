<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List,java.io.*" %>
<%List<User> userList = (ArrayList<User>) request.getAttribute("userList"); %>
<%String name = (String)session.getAttribute("UserName");%>
<%String[] errorMessage = (String[])request.getAttribute("errorMessage");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>プロジェクトの作成</title>
    <link rel="stylesheet" type="text/css" href="css/CreateProject.css">
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
        新規プロジェクト作成
            <font size="small">
                　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
                作成したプロジェクトは【予定】になります。編集から状態を変更してください。
            </font>
    </div>

    <form action="/SpringWork2020G1/CreateProject" name="formName" method="post">
        <!-- <div class="tag">
            氏名    <%if(!(errorMessage[0].equals("null"))){%>
              <font color="red"><%=errorMessage[0]%></font><%}%><br>
        </div>
        <input type="text" placeholder="　全角半角1文字以上20文字以下" name="name" required/><br> -->

        <div class="tag">
            タイトル
        </div>
        <input type="text" id="title" name="title" placeholder="30文字以内で記載してください" maxlength="30" minlength="1" pattern=".*\S+.*"　size="35" required>
        <div class="tag">
            概要
        </div>
        <textarea class="uk-textarea" rows="4" type="text" id="overview" name="overview" placeholder="300文字以内で記載してください"　maxlength="300" size="80" pattern=".*\S+.*" required></textarea>
        <div class="tag">
            参加者
        </div>
        <select id="user" name="user" multiple>
            <%for(int count=0;count<=userList.size()-1;count=count+1){%>
            <%User use = userList.get(count);%>
            <%if(name.equals(use.getName())){}else{%>
            <option value="<%=use.getId()%>"><%=use.getName()%></option>
            <%}%>
            <%}%>
        </select>
        <div class="tag">
            期日
        </div>
        <%if(!(errorMessage[0].equals("null"))){%>
        <font color="red"><%=errorMessage[0]%></font><%}%>
        <input type="date" id="deadline" name="deadline" value="2020-10-21"><br>
        <button type="submit" onclick="history.back()">戻る</button>
        <button type="submit">作成</button>
        </form>
        
    </body>
</html>