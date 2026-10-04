<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Save Result</title></head>
<body>
<h1>Save Result</h1>

<s:actionmessage/>

<h2>提交的数据</h2>
<ul>
    <li>name: <s:property value="user.name"/></li>
    <li>password: <s:property value="user.password"/></li>
    <li>age: <s:property value="user.age"/></li>
    <li>gender: <s:property value="user.gender"/></li>
    <li>vip: <s:property value="user.vip"/></li>
    <li>city: <s:property value="user.city"/></li>
    <li>bio: <s:property value="user.bio"/></li>
</ul>

<p><a href="input.action">返回表单</a></p>
</body>
</html>
