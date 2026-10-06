<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSF Result</title>
</head>
<body>
<f:view>
    <h:form>
        <h3><h:outputText value="JSF 组件渲染成功"/></h3>
        <p>Struts Action 的字段：
            <s:property value="jsfOutcome"/>
        </p>
        <p>JSF ManagedBean 的字段：
            <h:outputText value="#{msg.text}"/>
        </p>
    </h:form>
</f:view>
</body>
</html>