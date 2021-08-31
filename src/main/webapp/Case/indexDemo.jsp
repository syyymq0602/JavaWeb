<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./css/toastr.min.css">
    <script src="./js/toastr.min.js"></script>
    <script type="text/javascript">
        window.onload = function (){
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-bottom-center",
                "preventDuplicates": true,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "800",
                "timeOut": "3000",
                "extendedTimeOut": "300",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("登录成功")
        }
    </script>
</head>
<body>
<div class="text-center">
    <a
        href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
    </a>
</div>
</body>
</html>
