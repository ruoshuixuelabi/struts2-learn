<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Advanced Action Demo</title></head>
<body>

<h1>Demo 13: 高级 Action 特性</h1>

<h2>Preparable</h2>
<ul>
    <li>
        <s:url action="preparable" method="edit" var="p1">
            <s:param name="id">42</s:param>
        </s:url>
        <a href="${p1}">preparable!edit.action?id=42</a> — prepare() 钩子加载 user
    </li>
</ul>

<h2>ModelDriven</h2>
<form action="model-driven!save.action" method="post">
    id: <input name="id" value="100"/><br/>
    name: <input name="name" value="Alice"/><br/>
    age: <input name="age" value="25"/><br/>
    <button type="submit">提交（参数绑定到 User model）</button>
</form>

<h2>ValidationAware</h2>
<form action="validation-aware!check.action" method="post">
    username: <input name="username"/><br/>
    age: <input name="age"/><br/>
    <button type="submit">提交（校验会触发 addFieldError）</button>
</form>

</body>
</html>