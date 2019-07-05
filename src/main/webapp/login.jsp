<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/3
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/user/user" method="post">
    账号：<input type="text" name ="userName"  > <br>
    密码：<input type="password" name ="userPassword">
    <input type="submit" value=" 登陆 " >
</form>

</body>
</html>
