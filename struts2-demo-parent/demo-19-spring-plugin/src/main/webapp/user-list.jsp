<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>User List</title></head>
<body>
<h1>用户列表（来自 Spring 注入的 Service）</h1>
<ul>
<s:iterator value="users" var="u">
    <li>${u}</li>
</s:iterator>
</ul>
<p><a href="user-show.action">查看单个用户</a></p>
</body>
</html>