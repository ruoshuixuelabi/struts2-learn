<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>CDI Demo</title></head>
<body>
<h1>${injectedInfo}</h1>
<h2>用户列表（${users.size()}）</h2>
<ul>
    <s:iterator value="users">
        <li><s:property value="id"/> - <s:property value="username"/></li>
    </s:iterator>
</ul>
</body>
</html>
