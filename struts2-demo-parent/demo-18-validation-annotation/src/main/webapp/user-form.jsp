<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>User Form (JSR-303)</title></head>
<body>
<h2>新建用户（JSR-303 注解校验）</h2>
<s:form action="user-save">
    <s:textfield name="username" label="用户名 (3-20)"/>
    <s:textfield name="email" label="邮箱"/>
    <s:textfield name="age" label="年龄 (0-150)"/>
    <s:submit value="保存"/>
</s:form>
<s:fielderror/>
<p><a href="person-form.jsp">切换到 Struts 注解版</a></p>
</body>
</html>