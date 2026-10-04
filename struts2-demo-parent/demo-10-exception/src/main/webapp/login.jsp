<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Login Required</title></head>
<body>

<h1>请重新登录</h1>

<s:property value="exception.message"/>

<p><a href="home.action">返回首页</a></p>

</body>
</html>