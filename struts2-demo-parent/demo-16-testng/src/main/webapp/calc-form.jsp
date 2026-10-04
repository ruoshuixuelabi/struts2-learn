<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Calc Form</title></head>
<body>
<h2>四则运算</h2>
<s:form action="calc">
    <s:textfield name="a" label="a"/>
    <s:textfield name="b" label="b"/>
    <s:select name="op" label="op" list="#{'add':'加','sub':'减','mul':'乘','div':'除'}"/>
    <s:submit value="计算"/>
</s:form>
<s:fielderror/>
<s:actionerror/>
</body>
</html>