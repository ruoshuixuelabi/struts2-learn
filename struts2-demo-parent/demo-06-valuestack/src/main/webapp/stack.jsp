<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Demo 06: ValueStack</title></head>
<body>
<h1>ValueStack Demo</h1>
<h2>1. Action 属性（s:property）</h2>
<p>message = <s:property value="message"/></p>
<p>EL: ${message}</p>
<h2>2. push 临时对象（栈顶）</h2>
<p>label = <s:property value="label"/></p>
<h2>3. set 不增加栈深度</h2>
<p>greeting = <s:property value="greeting"/></p>
<h2>4. 访问 #session</h2>
<p>#session.userName = <s:property value="#session.userName"/></p>
<h2>5. 迭代 list（items）</h2>
<ul>
    <s:iterator value="items" status="st">
        <li>[${st.index}] <s:property/></li>
    </s:iterator>
</ul>
</body>
</html>
