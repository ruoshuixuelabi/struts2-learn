<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Redirect Target</title></head>
<body>

<h1>redirect-target.jsp</h1>
<p>URL 已变成 <code>/redirect-target.jsp</code>。原 request 域已丢失，message 改为从 session 读：</p>
<blockquote>${sessionMsg}</blockquote>
<p><a href="index.jsp">返回</a></p>

</body>
</html>