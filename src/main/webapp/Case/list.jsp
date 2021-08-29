<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script src="./js/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="./css/sweetalert2.min.css">
    <script type="text/javascript">
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        function del(){
            let flag = false;
            let cbs = document.getElementsByName("uid");
            for (let i = 0; i < cbs.length; i++) {
                if(cbs[i].checked){
                    flag=true;
                    break;
                }
            }
            if(flag){
                document.getElementById("list_form").submit();
            }
        }

        function skip(id){
            window.location.href = "${pageContext.request.contextPath}/delUserServlet?id=" + id;
        }

        function tip(id){
            swalWithBootstrapButtons.fire({
                title: '确定要删除吗?',
                text: "Are you sure to delete it?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '确认删除',
                cancelButtonText: '手滑',
                reverseButtons: true,
                width:'450px',
            }).then((result) => {
                if (result.isConfirmed) {
                    swalWithBootstrapButtons.fire(
                        '已删除',
                        'The user information has been deleted.',
                        'success'
                    )
                    window.setTimeout(function () {
                        skip(id);
                    },2000)
                }
            })
        }

        window.onload = function (){
            document.getElementById("deleteSelected").onclick = function () {
                swalWithBootstrapButtons.fire({
                    title: '确定要删除选中内容吗?',
                    text: "Are you sure to delete them?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '确认删除',
                    cancelButtonText: '手滑',
                    reverseButtons: true,
                    width:'450px',
                }).then((result) => {
                    if (result.isConfirmed) {
                        let flag = false;
                        let cbs = document.getElementsByName("uid");
                        for (let i = 0; i < cbs.length; i++) {
                            if(cbs[i].checked){
                                flag=true;
                                break;
                            }
                        }
                        if(flag){
                            swalWithBootstrapButtons.fire(
                                '已删除',
                                'The user information has been deleted.',
                                'success'
                            );
                            window.setTimeout(function () {
                                document.getElementById("list_form").submit();
                            }, 2000)
                        }else{
                            swalWithBootstrapButtons.fire(
                                '无效操作',
                                'Invalid operation',
                                'error'
                            );
                        }
                    }
                })
            }
            document.getElementById("firstCb").onclick = function (){
                let cbs = document.getElementsByName("uid");
                for (let i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
    <style>
        td, th {
            text-align: center;
        }
        html{
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float:left">
        <form class="form-inline" style="margin: 2px" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">姓名</label>
                <input type="text" name="name" class="form-control" id="exampleInputName1">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">籍贯</label>
                <input type="text" name="address" class="form-control" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="email" class="form-control" id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/Case/add.jsp">添加联系人</a>
        <a class="btn btn-primary" id="deleteSelected">删除选中</a>
    </div>
    <form action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post" id="list_form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" aria-label="Checkbox for following text input" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}" aria-label="Checkbox for following text input"></td>
                    <td>${s.count + (pb.currentPage - 1)*5}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" onclick="tip(${user.id})">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>


    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                        <span aria-hidden="true">&laquo;</span>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?rows=5&currentPage=${pb.currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?rows=5&currentPage=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?rows=5&currentPage=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pb.currentPage == pb.totalPage}">
                    <li class="disabled">
                        <span aria-hidden="true">&raquo;</span>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage != pb.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?rows=5&currentPage=${pb.currentPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px;margin-left:5px">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
