<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="example.domain.Person" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EL+JSTL综合案例</title>
    <style>
        table{
            border: 1px solid black;
            text-align: center;
            width: 500px;
            margin: 10px auto 0px auto;
        }
    </style>
</head>
<body>
<%
    List perList = new ArrayList();
    perList.add(new Person("张三",23,new Date()));
    perList.add(new Person("李四",26,new Date()));
    perList.add(new Person("王五",22,new Date()));
    request.setAttribute("list",perList);
%>

<table>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>生日</th>
    </tr>
    <c:forEach items="${list}" var="person" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${person.name}</td>
            <td>${person.age}</td>
            <td>${person.birthDay}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
