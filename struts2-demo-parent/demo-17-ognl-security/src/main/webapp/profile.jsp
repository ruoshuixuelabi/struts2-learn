<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Profile</title></head>
<body>
<h1>用户画像</h1>
<p>username: ${username}</p>
<p>email: ${email}</p>
<p>displayName: ${displayName}</p>
<s:fielderror/>
</body>
</html>