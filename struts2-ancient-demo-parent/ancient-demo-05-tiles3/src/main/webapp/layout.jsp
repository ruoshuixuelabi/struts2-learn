<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head><title><tiles:insertAttribute name="title"/></title></head>
<body>
<div style="border:2px solid #333;padding:10px;margin:10px">
    <tiles:insertAttribute name="body"/>
</div>
<p style="color:gray">页面框架来自 layout.jsp —— Tiles 布局生效的标志</p>
</body>
</html>