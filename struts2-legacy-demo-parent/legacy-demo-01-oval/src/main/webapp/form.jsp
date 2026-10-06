<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>Legacy Demo 01: OVal Validation</title>
</head>
<body>
<h1>OVal 注解校验（Struts 2.5.30）</h1>

<s:actionerror/>
<s:fielderror/>

<s:form action="user" method="post" namespace="/">
    <s:textfield name="user.username" label="用户名" requiredLabel="true"/>
    <s:textfield name="user.email"    label="邮箱"/>
    <s:submit value="保存"/>
</s:form>

<p style="color:gray">
    OVal 注解规则（来自 User.java）：
    <ul>
        <li>@NotNull 用户名</li>
        <li>@Length(min=3, max=20) 用户名长度</li>
        <li>@Email 邮箱格式</li>
    </ul>
</p>
</body>
</html>
