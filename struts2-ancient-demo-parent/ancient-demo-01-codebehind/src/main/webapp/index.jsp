<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index - CodeBehind Demo</title>
</head>
<body>
<h2>这是 /WEB-INF/content/index.jsp</h2>
<p>能看到这行字，说明 CodeBehind 的"约定找 JSP"生效了——你访问的
    <code>/index.action</code> 在 struts.xml 里根本没配置，插件按命名约定找到了这个同名 JSP。</p>
<ul>
    <li><a href="${pageContext.request.contextPath}/hello.action">测试显式配置：hello → /hello.jsp</a></li>
    <li><a href="${pageContext.request.contextPath}/index.action">测试约定映射：index → 本页</a></li>
</ul>
</body>
</html>