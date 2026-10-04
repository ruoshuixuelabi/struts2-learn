<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <style>
        body { font-family: sans-serif; }
        header, footer { background: #eee; padding: 12px; }
        nav { background: #fafafa; padding: 8px; width: 200px; float: left; }
        .content { margin-left: 220px; padding: 12px; }
    </style>
</head>
<body>
    <tiles:insertAttribute name="header"/>
    <div class="main">
        <tiles:insertAttribute name="menu"/>
        <div class="content">
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
    <tiles:insertAttribute name="footer"/>
</body>
</html>