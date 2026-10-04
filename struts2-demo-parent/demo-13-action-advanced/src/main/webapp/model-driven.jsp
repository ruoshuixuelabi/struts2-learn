<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>ModelDriven Result</title></head>
<body>

<h1>ModelDriven save 成功</h1>
<p>user（从 model 读取） = <code>${user}</code></p>
<p>注意：Action 没有 setUser()，但 params 已绑定到 ModelDriven 的 getModel() 返回的对象。</p>

<p><a href="index.jsp">返回</a></p>

</body>
</html>