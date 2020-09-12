<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.InputError" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<% InputError err = (InputError) request.getAttribute("error");%>


<html>
<html>
<head>
    <title>ログイン</title>
</head>
<body>
<div class="SignInForm">
    <form action="/todoprj/SignIn" method="post">
        <div class="field" style="margin-left: 20px; margin-top: 10px">
            ユーザID <input type ="text" name="loginid">
        </div>
        <div class="field" style="margin-left: 20px">
            パスワード <input type ="password" name="password">
        </div>
        <div>
            <input type="submit" value="送信" style="margin-left: 60%">
        </div>
        <!--<div>
            <span style="font-size: xx-small; color: red; ">
            <% if (err != null && err.getWrongMailOrPassword()!= null){%>
            <%=StringEscapeUtils.escapeHtml4(err.getWrongMailOrPassword())%>
            <%}%>
            </span>
        </div>-->
    </form>
</div>
</body>
</html>