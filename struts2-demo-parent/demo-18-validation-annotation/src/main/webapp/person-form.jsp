<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Person Form (Struts 注解)</title></head>
<body>
<h2>新建人员（Struts 注解校验）</h2>
<s:form action="person-save">
    <s:textfield name="name" label="姓名 (2-10)"/>
    <s:textfield name="age" label="年龄 (0-150)"/>
    <s:submit value="保存"/>
</s:form>
<s:fielderror/>
<p><a href="user-form.jsp">切换到 JSR-303 注解版</a></p>
</body>
</html>