<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务执行中 - demo-35</title>
    <!--
        关键：executeAndWait 拦截器要求 meta refresh 跳回**同一个** long-task.action URL（带原参数）。
        拦截器会读 session 里的 BackgroundProcess，判断是否完成：
          - 未完成 → 再返回 "wait"（继续 wait.jsp 渲染）
          - 完成   → 返回 bp.getResult()（= SUCCESS）→ result.jsp
        跳到别的 action（如 progress.action）会让链路断裂。
    -->
    <meta http-equiv="refresh" content="2;url=<s:url action="long-task" namespace="/async" includeParams="all"/>">
</head>
<body>
    <h1>任务执行中...</h1>
    <p>TaskId: <code>${taskId}</code></p>
    <p>当前进度：<strong>${progress}%</strong></p>
    <p>页面 2 秒后自动重新查询任务状态。</p>
    <p><a href="<s:url action="long-task" namespace="/async" includeParams="all"/>">手动刷新</a></p>
</body>
</html>