<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Demo 07: OGNL</title></head>
<body>
<h1>OGNL Demo (Struts 7.3.0)</h1>

<h2>1. Action 属性</h2>
<p>message = <s:property value="message"/></p>
<p>message.toUpperCase() = <s:property value="message.toUpperCase()"/></p>

<h2>2. 数学运算</h2>
<p>price * quantity = <s:property value="price * quantity"/></p>

<h2>3. 访问 ContextMap</h2>
<p>#session.userName = <s:property value="#session.userName"/></p>
<p>fromRequest = <s:property value="#request.fromRequest"/></p>

<h2>4. 集合过滤（age &gt; 18）</h2>
<ul>
    <s:iterator value="users.{?age > 18}">
        <li><s:property value="name"/> (<s:property value="age"/>)</li>
    </s:iterator>
</ul>

<h2>5. 集合投影（只取 name）</h2>
<ul>
    <s:iterator value="users.{name}">
        <li><s:property/></li>
    </s:iterator>
</ul>

<h2>6. 第一个元素 / 最后一个元素</h2>
<p>first = <s:property value="users.^{name}"/></p>
<p>last = <s:property value="users.${name}"/></p>

<h2>7. 静态字段（需 enableStaticMethodAccess=true）</h2>
<p>@java.lang.Math@PI = <s:property value="@java.lang.Math@PI"/></p>

</body>
</html>
