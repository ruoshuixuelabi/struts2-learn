<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>redirect 成功落地</h2>
<p>浏览器地址栏现在是 redirect-target.jsp —— 客户端重定向（302）的证据。</p>
<p>关键点：redirect 后是一次<b>全新请求</b>，上一个 Action 的值栈数据不会带过来。</p>
<a href="index.action">返回首页</a>
</body>
</html>