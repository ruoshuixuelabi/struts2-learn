<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>验证成功</title></head>
<body>
    <h1>Bean-Validation 验证通过</h1>
    <ul>
        <li>用户名：<s:property value="username" /></li>
        <li>邮箱：<s:property value="email" /></li>
        <li>年龄：<s:property value="age" /></li>
        <li>nested.username：<s:property value="nestedUser.username" /></li>
        <li>nested.email：<s:property value="nestedUser.email" /></li>
    </ul>
</body>
</html>