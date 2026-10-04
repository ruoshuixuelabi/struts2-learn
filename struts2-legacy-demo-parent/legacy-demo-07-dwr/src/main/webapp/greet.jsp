<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Struts 2 + DWR 远程调用</title>
    <!-- DWR 自动生成的 JS 接口：访问 /dwr/interface/GreeterService.js 即可 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/GreeterService.js"></script>
</head>
<body>
<h2>Struts 2 + DWR AJAX 示例（7.x 已移除此插件）</h2>
<input type="text" id="name" placeholder="输入你的名字"/>
<button onclick="callGreet()">远程问候</button>
<button onclick="callAdd()">5 + 7 = ?</button>
<pre id="output"></pre>

<script type="text/javascript">
    function callGreet() {
        var name = document.getElementById('name').value;
        GreeterService.greet(name, function(data) {
            document.getElementById('output').textContent = data;
        });
    }
    function callAdd() {
        GreeterService.add(5, 7, function(data) {
            document.getElementById('output').textContent = '5 + 7 = ' + data;
        });
    }
</script>
</body>
</html>
