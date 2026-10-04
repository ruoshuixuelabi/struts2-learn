<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<h1>用户列表</h1>
<p>本页面通过 Tiles 渲染：definition=<code>user.list.tiles</code>，使用自定义 menu 片段。</p>
<ul>
    <s:iterator value="users">
        <li><s:property/></li>
    </s:iterator>
</ul>