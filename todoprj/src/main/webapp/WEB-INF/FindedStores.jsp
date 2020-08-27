<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="beans.Store"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%List<Store> list = (List<Store>) request.getAttribute("StoreList");%>

<HTML>
  <HEAD>
    <TITLE></TITLE>
  </HEAD>
    <body>
    
  

    <P align="center">
      <FONT size="4"
        ><BR />
        <BR />
        検索結果</FONT></P>

        <table border="1">
          <tr>
            <th>店名</th>
            <th>概要</th>
            <th>お気に入り登録</th>

          </tr>
        <c:forEach var="Store" items="${StoreList}">
          <tr>
            <td>${Store.storeName}</td>
            <td>${Store.storeInfo}</td>
            <td>
              <form action="./FavStore" method="post">
              <input type="submit" name="OK" value="登録"/>
            </form>
            </td>
          </tr>
        </c:forEach>
        </table>
    
        <a href="/BunG/AuthUser">トップへ戻る </a><br />
      </body>
</HTML>
