<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Struts 2 + Portlet + Tiles 门户示例</title>
</head>
<body>
<div class="portal-header">
    <tiles:insertAttribute name="header"/>
</div>
<div class="portal-body">
    <tiles:insertAttribute name="body"/>
</div>
<div class="portal-footer">
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>
