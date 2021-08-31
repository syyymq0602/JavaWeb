<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login_index</title>
    <style>
        #form{
            width: 500px;
            /*border: 1px red solid;*/
            margin: 100px auto 0px;
        }
        table{
            margin: 10px auto;
        }
        .info{
            /*border: 1px red solid;*/
            width: 500px;
            color: red;
            text-align: center;
            margin: 2px auto;
        }
    </style>
</head>
<body>
    <div id="form">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <table>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>验证码</td>
                    <td><input type="text" name="checkCode"></td>
                </tr>
                <tr>
                    <td colspan="2"><img src="${pageContext.request.contextPath}/check" id="image"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="登录"></td>
                </tr>
            </table>
        </form>
    </div>

    <div class="info"><%=request.getAttribute("error") == null ? "" :request.getAttribute("error") %></div>
    <div class="info"><%=request.getAttribute("login_error") == null ? "" :request.getAttribute("login_error") %></div>

<br/>
<a href="HTML/downLoad.html">Hello Servlet</a><br/>
<a href="JSP/el_jstl.jsp">EL+JSTL案例</a><br/>
<a href="Case/indexDemo.jsp">案例跳转入口</a><br/>
<a href="Case/login.jsp">登录界面入口</a><br/>
</body>

<script>
    window.onload = function (){
        document.getElementById("image").onclick = function () {
            this.src = "/demo/check?time=" + new Date().getTime();
        }
    }
</script>
</html>