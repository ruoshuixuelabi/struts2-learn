<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Greet Form</title></head>
<body>
<h2>输入姓名</h2>
<s:form action="greet">
    <s:textfield name="name" label="姓名"/>
    <s:submit value="问候"/>
</s:form>
<s:fielderror/>
</body>
</html>