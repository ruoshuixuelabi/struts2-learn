<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<body>
<h1>Struts 1.3.10 Hello World</h1>
<html:form action="/hello">
    Name: <html:text property="name"/>
    <html:submit value="Greet"/>
</html:form>
</body>
</html>
