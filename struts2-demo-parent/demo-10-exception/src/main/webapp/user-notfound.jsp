<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>User Not Found</title></head>
<body>

<h1>用户未找到</h1>

<p>捕获到的自定义异常 <code>UserNotFoundException</code>：</p>

<s:property value="exception.message"/>
<p>userId = <s:property value="exception.userId"/></p>

<p><a href="home.action">返回首页</a></p>

</body>
</html>