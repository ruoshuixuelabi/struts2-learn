<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
<body>
<h1>Struts 1 ActionForm 校验演示</h1>
<html:messages id="err">
    <div style="color:red">• ${err}</div>
</html:messages>
<html:form action="/register">
    Username: <html:text property="username"/><br/>
    Email: <html:text property="email"/><br/>
    Age: <html:text property="age"/><br/>
    <html:submit value="Register"/>
</html:form>
</body>
</html>