<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>Legacy Demo 01: Success</title>
</head>
<body>
<h1>OVal 校验通过 ✓</h1>

<p>用户名：<s:property value="user.username"/></p>
<p>邮箱：<s:property value="user.email"/></p>

<p style="color:gray">由 struts2-oval-plugin 的 ovalValidation 拦截器校验通过后跳转到本视图。</p>

<a href="user.action">返回表单</a>
</body>
</html>
