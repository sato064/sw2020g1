<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    国分寺グルメ情報共有システム"BunG"
    <form action="./AuthUser" method="post">
      Name <input type="text" name="usr_name" /><br />
      password<input type="password" name="usr_pass" /><br />

      <input type="submit" name="OK" value="ログイン"/>
    </form>
    <br />
    <a href="/BunG/regist.jsp">新規登録 </a><br />
    
  
    
    

  </body>
</html>
