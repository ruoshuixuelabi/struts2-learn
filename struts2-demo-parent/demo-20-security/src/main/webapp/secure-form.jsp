<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Secure Form</title></head>
<body>
<h1>安全加固 Demo</h1>
<h3>配置：strictMethodInvocation + OGNL allowlist + excludedClasses + 黑名单拦截器</h3>

<h4>1. 正常调用（成功）</h4>
<s:form action="secure-action">
    <s:textfield name="username" label="用户名 (必填)"/>
    <s:textfield name="token" label="token (可选)"/>
    <s:submit value="提交"/>
</s:form>
<s:fielderror/>

<h4>2. 调用 info 方法（allowedMethod）</h4>
<p><a href="secure-info.action">secure-info.action → SecureAction.info()</a></p>

<h4>3. 攻击测试（应被拦截）</h4>
<ul>
    <li><a href="secure-action.action?username=alice&amp;cmd=%40java.lang.Runtime%40getRuntime().exec('id')">Runtime exec 注入</a></li>
    <li><a href="secure-action.action?username=alice&amp;p=%23_memberAccess%3D%40java.lang.Runtime%40getRuntime().exec('id')">#_memberAccess 注入</a></li>
</ul>
</body>
</html>