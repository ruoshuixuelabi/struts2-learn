<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Upload Failed</title></head>
<body>

<h1>上传失败</h1>

<s:actionerror/>
<s:fielderror/>

<p><a href="index.jsp">重试</a></p>

</body>
</html>