<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Struts Lifecycle</title></head>
<body>
<h1>Struts 7.3.0 Request Lifecycle Demo</h1>
<p><strong>Message:</strong> <s:property value="message"/></p>
<p>查看 IDE / Maven 控制台日志，观察拦截器链"洋葱模型"调用顺序。</p>
</body>
</html>
