<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/updateUser.js"></script>
    <style>
        .error{
            font-size: 15px;
            color: red;
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
        <%-- 隐藏域--%>
        <input type="hidden" name="id" value="${user.id}">

        <div class="form-group">
            <label for="update_name">姓名：</label>
            <input type="text" class="form-control" id="update_name" name="name" value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender == 'male'}">
                <label class="radio-inline">
                    <input type="radio" name="gender" value="male" checked="checked"/> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="female"/> 女
                </label>
            </c:if>
            <c:if test="${user.gender == 'female'}">
                <label class="radio-inline">
                    <input type="radio" name="gender" value="male"/> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="female" checked="checked"/> 女
                </label>
            </c:if>
        </div>

      <div class="form-group">
          <label for="update_age">年龄：</label>
          <input type="text" class="form-control" id="update_age" value="${user.age}" name="age" placeholder="请输入年龄" />
          <span id="update_span_age" class="error"></span>
      </div>

      <div class="form-group">
        <label for="address">籍贯：</label>
          <select name="address" class="form-control" id="address">
              <option value="Shaanxi">陕西</option>
              <option value="Peking">北京</option>
              <option value="Shanghai">上海</option>
              <option value="Sichuan">四川</option>
              <option value="Nanjing">南京</option>
              <option value="Chongqing">重庆</option>
              <option value="Zhejiang">浙江</option>
              <option value="Yunnan">云南</option>
          </select>
      </div>

      <div class="form-group">
          <label for="update_qq">QQ：</label>
          <input type="text" id="update_qq" class="form-control" value="${user.qq}" name="qq" placeholder="请输入QQ号码"/>
          <span id="update_span_qq" class="error"></span>
      </div>

      <div class="form-group">
          <label for="update_email">Email：</label>
          <input type="text" id="update_email" class="form-control" value="${user.email}" name="email" placeholder="请输入邮箱地址"/>
          <span id="update_span_email" class="error"></span>
      </div>

         <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" id=btn_submit_update" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
         </div>
    </form>
    </div>
</body>

</html>