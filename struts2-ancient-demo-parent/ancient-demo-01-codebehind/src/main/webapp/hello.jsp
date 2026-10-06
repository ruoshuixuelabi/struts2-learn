<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello - CodeBehind Demo</title>
</head>
<body>
<h2>HelloAction 输出</h2>
<p>message (Struts 标签)：<s:property value="message"/></p>
<p>message (EL)：${message}</p>
<hr>
<a href="<s:url action='index'/>">返回 Index（测试 CodeBehind 约定映射）</a>
</body>
</html>