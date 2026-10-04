<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<body>
<h1>Struts 1 ActionForm 校验演示</h1>
<html:errors/>
<html:form action="/register">
    Username: <html:text property="username"/><br/>
    Email: <html:text property="email"/><br/>
    Age: <html:text property="age"/><br/>
    <html:submit value="Register"/>
</html:form>
</body>
</html>
