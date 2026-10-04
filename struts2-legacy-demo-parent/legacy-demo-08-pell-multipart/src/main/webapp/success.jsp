<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上传成功</title>
</head>
<body>
<h2>上传成功</h2>
<ul>
    <li>文件名：<s:property value="uploaded.name"/></li>
    <li>大小：<s:property value="uploaded.size"/> 字节</li>
    <li>类型：<s:property value="uploaded.contentType"/></li>
</ul>
<a href="form.jsp">返回</a>
</body>
</html>
