<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Error</title></head>
<body>

<h1>出错了</h1>

<!-- 显示通过 addActionError() 添加的消息 -->
<s:actionerror/>

<!-- 显示当前异常对象的 message（devMode 下自动可见） -->
<p>异常信息：<s:property value="exception.message"/></p>

<!-- 仅 devMode=true 时显示堆栈 -->
<p>异常堆栈（devMode）：</p>
<pre><s:property value="exceptionStack"/></pre>

<p><a href="home.action">返回首页</a></p>

</body>
</html>