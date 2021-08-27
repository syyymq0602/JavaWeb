
window.onload = function () {
    let button = document.getElementById("btn_submit")
    document.getElementById("name").onblur = function () {
        if(!checkName()){
            document.getElementById("span_name").innerHTML = "输入用户名格式有误";
            button.setAttribute("disabled", "disabled");
        }else {
            document.getElementById("span_name").innerHTML = "";
            if (checkEmail() && checkAge() && checkQQ()) {
                button.removeAttribute("disabled");
            }
        }
    }
    document.getElementById("age").onblur = function () {
        if(!checkAge()){
            document.getElementById("span_age").innerHTML = "输入年龄格式有误";
            button.setAttribute("disabled", "disabled");
        }else {
            document.getElementById("span_age").innerHTML = "";
            if (checkName() && checkEmail() && checkQQ()) {
                button.removeAttribute("disabled");
            }
        }
    }
    document.getElementById("qq").onblur = function () {
        if(!checkQQ()){
            document.getElementById("span_qq").innerHTML = "输入QQ格式有误";
            button.setAttribute("disabled", "disabled");
        }else {
            document.getElementById("span_qq").innerHTML = "";
            if (checkName() && checkAge() && checkEmail()) {
                button.removeAttribute("disabled");
            }
        }
    }
    document.getElementById("email").onblur = function () {
        if(!checkEmail()){
            document.getElementById("span_email").innerHTML = "输入邮箱格式有误";
            button.setAttribute("disabled", "disabled");
        }else {
            document.getElementById("span_email").innerHTML = "";
            if (checkName() && checkAge() && checkQQ()) {
                button.removeAttribute("disabled");
            }
        }
    }
}

// 用户名校验
function checkName() {
    let name = document.getElementById("name").value;
    // 4-15位字母
    let reg_name = /^[a-zA-Z]{4,15}$/;
    return reg_name.test(name);
}

// 年龄校验
function checkAge() {
    let age = document.getElementById("age").value;
    // 1-3位数字
    let reg_age = /^\d{1,3}$/;

    return reg_age.test(age);
}

// QQ校验
function checkQQ() {
    let qq = document.getElementById("qq").value;
    // 4-13位数字
    let reg_qq = /^\d{4,13}$/;
    return reg_qq.test(qq);
}

// 邮箱校验
function checkEmail() {
    let email = document.getElementById("email").value;
    // 开头不能是下划线，必须带有@标识，@后面可以多个.com/.cn之类的
    let reg_email = /^[a-zA-Z0-9]+\w+@\w{2,6}(\.\w{2,3})+$/;
    return reg_email.test(email);
}