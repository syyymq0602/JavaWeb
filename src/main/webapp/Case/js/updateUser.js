
window.onload = function () {
    let button = document.getElementById("btn_submit_update")
    document.getElementById("update_age").onblur = function () {
        if(!checkUpdateAge()){
            document.getElementById("update_span_age").innerHTML = "输入年龄格式有误";
            button.setAttribute("disabled", "disabled");
        }else {
            document.getElementById("update_span_age").innerHTML = "";
            if(checkUpdateQQ() && checkUpdateEmail()){
                button.removeAttribute("disabled");
            }
        }
    }
    document.getElementById("update_qq").onblur = function () {
        if (!checkUpdateQQ()) {
            document.getElementById("update_span_qq").innerHTML = "输入QQ格式有误";
            button.setAttribute("disabled", "disabled");
        } else {
            document.getElementById("update_span_qq").innerHTML = "";
            if (checkUpdateAge() && checkUpdateEmail()) {
                button.disabled = false;
            }
        }
    }
    document.getElementById("update_email").onblur = function () {
        if (!checkUpdateEmail()) {
            document.getElementById("update_span_email").innerHTML = "输入邮箱格式有误";
            button.setAttribute("disabled", "disabled");
        } else {
            document.getElementById("update_span_email").innerHTML = "";
            if (checkUpdateAge() && checkUpdateQQ()) {
                button.disabled = false;
            }
        }
    }
}




// 更新页面的年龄检测
function checkUpdateAge(){
    let age = document.getElementById("update_age").value;
    // 1-3位数字
    let reg_age = /^\d{1,3}$/;

    return reg_age.test(age);
}

// QQ校验
function checkUpdateQQ() {
    let qq = document.getElementById("update_qq").value;
    // 4-13位数字
    let reg_qq = /^\d{4,13}$/;
    return reg_qq.test(qq);
}

// 邮箱校验
function checkUpdateEmail() {
    let email = document.getElementById("update_email").value;
    // 开头不能是下划线，必须带有@标识，@后面可以多个.com/.cn
    let reg_email = /^[a-zA-Z0-9]+\w+@\w{2,6}(\.\w{2,3})+$/;
    return reg_email.test(email);
}