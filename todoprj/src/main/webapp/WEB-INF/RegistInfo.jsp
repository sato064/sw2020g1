<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    新規店舗情報の登録<br />
    <form action="./RegistInfo" method="post">
      店舗名<input type="text" name="str_name" /><br>

      店舗詳細
      <input type="text" name="str_info" size = "40"/><br>
      <br>
      <input type="submit" name="OK" value="登録"/>
    </form>
    
  </body>
</html>
