<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>用户列表</title>
<h1>用户列表</h1>
<ul>
    <s:iterator value="users">
        <li><s:property/></li>
    </s:iterator>
</ul>