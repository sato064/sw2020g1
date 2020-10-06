<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Restaurant,java.util.List" %>
<% List<Restaurant> list = (List<Restaurant>) request.getAttribute("list");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/registReview.css">
  </head>
  <body>
    <a href="/Gourmet/Logout">ログアウトする</a>
    <p class="kokubunji">KOKUBUNJI</p>
    <form action="./SearchInfo" method="GET">
      <input type="submit" value="　グルメ情報をみる　" name="OK" />
    </form>
    <br><br>
    <form action="./RegistReview" method="post">
      <input type="hidden" name="user_id"  value=${login_user.getId()}><br>
      飲食店
      <select name="restaurant_id">
        <% for(Restaurant restaurant: list){ %>
        <option value=<%=restaurant.getId()%>><%=restaurant.getName()%></option>
        <% } %>
      </select>
      <a href="./RegistRestaurant">飲食店の登録</a>
      <br><br>
      シーン
      <select name="scene"　required>
        <option value="Morning">Morning</option>
        <option value="Lunch">Lunch</option>
        <option value="Dinner">Dinneer</option>
      </select>
      　　　　　　　　　　　　　　　　　　　　　<br><br>
      <input type="text" placeholder="グルメ情報" name="information" style="width: 310px;" required>
      <br><br>
      <input type="submit" value="グルメ情報を投稿する" name="OK" />
    </form>
  </body>
</html>
