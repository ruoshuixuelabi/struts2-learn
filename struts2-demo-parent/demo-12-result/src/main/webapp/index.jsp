<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Result Types Demo</title></head>
<body>

<h1>Demo 12: 四种 ResultType</h1>

<ul>
    <li><a href="dispatcher.action">dispatcher.action</a> — dispatcher（默认转发）</li>
    <li><a href="redirect.action">redirect.action</a> — redirect（302 重定向）</li>
    <li><a href="chain-first.action">chain-first.action</a> — chain（链式 Action）</li>
    <li><a href="json.action">json.action</a> — json（返回 JSON）</li>
</ul>

</body>
</html>