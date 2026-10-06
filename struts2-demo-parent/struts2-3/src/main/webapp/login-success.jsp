<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
  </head>
  <body>
  欢迎：${sessionScope.username };
  <br>
  在线人数：${applicationScope.count };
  <br>
  <a href="logout.do">Logout</a>
  </body>
</html>
