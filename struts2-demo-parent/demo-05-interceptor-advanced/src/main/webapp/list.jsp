<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>User List</title></head>
<body>
<h1>User List</h1>
<ul>
    <s:iterator value="users">
        <li><s:property/></li>
    </s:iterator>
</ul>
<p><a href="user-add.action?userName=Dave">添加 Dave (触发 Auth)</a></p>
</body>
</html>
