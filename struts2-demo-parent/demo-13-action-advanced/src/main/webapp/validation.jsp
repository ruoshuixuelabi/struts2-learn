<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Validation Aware</title></head>
<body>

<h1>ValidationAware addFieldError 演示</h1>

<s:actionerror/>
<s:fielderror/>

<s:form action="validation-aware!check" method="post">
    <s:textfield name="username" label="用户名"/>
    <s:textfield name="age" label="年龄"/>
    <s:submit value="校验"/>
</s:form>

</body>
</html>