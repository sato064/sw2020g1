<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import = "model.Student" %>
<%@page import = "java.util.ArrayList" %>
<%@page import = "java.util.List" %>
<%Student userData = (Student) request.getAttribute("userData") ;%>
<%--<%int errorFlag = (int) request.getAttribute("errorFlag");%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新規ユーザ登録</title>
</head>
<body>
<div class="uk-sticky">
    <jsp:include page="topUikit.html"/>
</div>
<div class="uk-container">
    <div class="form">
        <%--    <%if(errorFlag!=1){%>--%>
        <form action="/TodoPrj/RegistInfo" method="post">
            <div class="uk-margin-auto">
                氏名<input type="text" placeholder="氏名" id="name" name="name" maxlength="20" minlength="1" pattern=".*\S+.*" required>
            </div>
            <div class="uk-margin-auto">
                ユーザーID<input type="email" placeholder="メールアドレスを入力してください" id="mail" name="mail" size="30" required>
            </div>
            <div class="uk-margin-auto">
                パスワード<input type="password" placeholder="8~16字半角英数" id="pass" name="pass"  maxlength="16" minlength="8" pattern=".*\S+.*" required>
            </div>
            <div class="uk-margin-auto">
                <button class="uk-button">登録</button>
            </div>
        </form>
<%--　ユーザIDが同じになった場合はエラーを出す(やらなくてもいい)　--%>
<%--    <%}else{%>--%>

<%--    <form action="/TodoPrj/registration" method="post">--%>
<%--        すでに登録されたアカウントです。別のメールアドレスでお試しください。<br>--%>
<%--        氏名<input type="text" placeholder="氏名" id="name" name="name" maxlength="20" minlength="1" pattern=".*\S+.*" required value = <%=userData.getName()%>><br>--%>
<%--        ユーザID<input type="email" placeholder="メールアドレス" id="mail" name="mail" required value = <%=userData.getMail()%>><br>--%>
<%--        パスワード<input type="password" placeholder="8~16字半角英数" id="pass" name="pass1" maxlength="16" minlength="8" pattern=".*\S+.*" required value = <%=userData.getPass()%>><br>--%>
<%--        <button>登録</button>--%>
<%--    </form>--%>
<%--    <%}%>--%>
    </div>
</div>
</body>
</html>

