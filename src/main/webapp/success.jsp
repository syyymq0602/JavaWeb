<%--
  Created by IntelliJ IDEA.
  User: PPG
  Date: 2021/8/25
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success Login</title>
</head>
<body>
    <h3><%=request.getSession().getAttribute("user")%>,欢迎你</h3>
</body>
</html>
