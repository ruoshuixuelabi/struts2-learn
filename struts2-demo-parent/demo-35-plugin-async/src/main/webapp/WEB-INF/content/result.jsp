<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务完成 - demo-35</title>
</head>
<body>
    <h1>任务完成！</h1>
    <p>TaskId: <code>${taskId}</code></p>
    <p>最终进度：<strong>${progress}%</strong></p>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
    <p><a href="<s:url action="task-create" namespace="/async"/>">启动另一个任务</a></p>
</body>
</html>