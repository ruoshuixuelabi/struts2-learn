<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<section>
    <p><s:property value="page.body"/></p>
    <s:form action="page!submit.action" method="post">
        <s:textfield name="page.title" label="标题"/>
        <s:textarea name="page.body" label="正文"/>
        <s:submit value="提交"/>
    </s:form>
</section>
