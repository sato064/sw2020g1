<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Studentをimportする -->
<%@ page import= "beans.Review,java.util.List" %>
<% List<Review> list = (List<Review>) request.getAttribute("list");%>

<html>
  <head>
    <title>国分寺周辺のグルメ情報</title>
    <link rel="stylesheet" type="text/css" href="css/showReview.css">
  </head>
  <body>
    <a href="/SpringWork2020G1/Logout">ログアウトする</a>
    <br>
    <p class="kokubunji">
      KOKUBUNJI
    </p>
    <a href="/SpringWork2020G1/Login">トップ画面に戻る</a>
    <br>
    <div class="sort">
      <form action="./SortReview" method="post">
        <select name="sort">
          <option value="oldist">投稿した順(番号順)</option>
          <option value="good_count">役に立ったポイントが高い順</option>
        </select>
        <input type="submit" value="並び替える" name="OK" />
      </form>
    </div>
    <div class="narrow">
      <form action="./SceneReview" method="post">
        <select name="scene">
          <option value="Morning">Morning</option>
          <option value="Lunch">Lunch</option>
          <option value="Dinner">Dinner</option>
        </select>
        <input type="submit" value="絞り込む" name="OK" /><br>
      </form>
    </div>
    <br><br><br>
    <table border="1" >
      <tr>
        <th>番号</th>
        <th>投稿者名</th>
        <th>店名</th>
        <th>シーン</th>
        <th>グルメ情報</th>
        <th>役に立ったポイント</th>
      </tr>
    <% for(Review review: list ){ %>
    <tr>
      <td><%=review.getId()%></td>
      <td><%=review.getUser().getName()%></td>
      <td><%=review.getRestaurant().getName()%></td>
      <td><%=review.getScene()%></td>
      <td><%=review.getInformation()%></td>
      <td><%=review.getGoodCount()%></td>
    </tr>
    <% } %>
    </table>
    <br />
    <form action="./PlusGood" method="post">
      <p class="good">
        役に立ったグルメ情報の番号を入力してください
        <input type="number" placeholder="半角数字" name="id" required/><br>
      </p>
      <input type="submit"  value="役に立ったポイントを送る" name="OK" />
    </form>
  </body>
</html>
