<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>User Form</title></head>
<body>
<h1>User Form (Struts 标签)</h1>

<s:form action="user-save" method="post">
    <s:textfield name="user.name" label="用户名" requiredLabel="true"/>
    <s:password name="user.password" label="密码"/>
    <s:textfield name="user.age" label="年龄" type="number"/>
    <s:radio name="user.gender" list="#{'M':'男', 'F':'女'}" label="性别"/>
    <s:checkbox name="user.vip" label="VIP 用户"/>
    <s:select name="user.city" list="cities" headerKey="" headerValue="请选择" label="城市"/>
    <s:textarea name="user.bio" label="简介" rows="3" cols="30"/>
    <s:submit value="提交"/>
    <s:reset value="重置"/>
</s:form>

<s:actionerror/>
<s:fielderror/>
</body>
</html>
