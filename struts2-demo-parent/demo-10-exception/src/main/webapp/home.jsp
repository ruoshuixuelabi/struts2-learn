<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Exception Demo - Home</title></head>
<body>

<h1>Demo 10: 异常处理</h1>
<%-- 异常映射发生时，ExceptionHolder 被压到栈顶，从这里显式取异常信息 --%>
<s:if test="exception != null">
    <p style="color:red">捕获异常：<s:property value="exception.message"/></p>
</s:if>

<s:actionerror/>

<ul>
    <li>
        <a href="<s:url action='user' method='delete'/>">user!delete.action</a>
        — 不带 id 参数 → 抛 IllegalArgumentException（Action 级 → input）
    </li>
    <li>
        <a href="<s:url action='user' method='login'/>">user!login.action</a>
        — 抛 SecurityException（Action 级 → login.jsp）
    </li>
    <li>
        <a href="<s:url action='user' method='throwNpe'/>">user!throwNpe.action</a>
        — 抛 NPE（全局 Throwable 兜底 → error.jsp）
    </li>
    <li>
        <a href="<s:url action='user' method='findUser'>
                  <s:param name='id'>42</s:param>
               </s:url>">user!findUser.action?id=42</a>
        — 抛 UserNotFoundException（全局业务异常映射 → user-notfound.jsp）
    </li>
</ul>

</body>
</html>