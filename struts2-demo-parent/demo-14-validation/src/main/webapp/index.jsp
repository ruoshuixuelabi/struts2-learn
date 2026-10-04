<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>XML Validation Demo</title></head>
<body>

<h1>Demo 14: XML 验证</h1>

<s:actionerror/>
<s:fielderror/>

<s:form action="user-save">
    <s:textfield name="username" label="用户名 (3-20 字符)"/>
    <s:textfield name="age" label="年龄 (0-150)"/>
    <s:submit value="提交"/>
</s:form>

</body>
</html>