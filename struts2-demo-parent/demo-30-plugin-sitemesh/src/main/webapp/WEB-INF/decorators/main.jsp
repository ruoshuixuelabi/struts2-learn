<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title/></title>
    <decorator:head/>
    <style>
        body { font-family: sans-serif; margin: 0; }
        header { background: #333; color: white; padding: 12px; }
        nav { background: #fafafa; padding: 8px; }
        main { padding: 16px; min-height: 300px; }
        footer { background: #333; color: white; padding: 12px; text-align: center; }
    </style>
</head>
<body>
    <header>
        <h2>SiteMesh 统一顶部导航（装饰器提供）</h2>
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/home.action">首页</a> |
        <a href="${pageContext.request.contextPath}/user-list.action">用户列表</a> |
        <a href="${pageContext.request.contextPath}/api/raw.action">裸 API</a>
    </nav>
    <main>
        <decorator:body/>
    </main>
    <footer>
        <p>SiteMesh 统一页脚（装饰器提供） - (c) 2026</p>
    </footer>
</body>
</html>