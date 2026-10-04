<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Blocked</title></head>
<body>
<h1 style="color:red">请求已被 OGNL 安全拦截器拦截</h1>
<p>检测到危险 OGNL payload，拒绝执行。</p>
<p><a href="profile.action?username=alice">返回安全示例</a></p>
</body>
</html>