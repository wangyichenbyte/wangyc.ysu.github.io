<%--
  Created by IntelliJ IDEA.
  User: 80739
  Date: 2023/6/6
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<!-- 注册页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>

    <style>
        body {
            background-color: #f2f2f2;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            width: 450px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            text-align: center;
        }
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .form-label {
            width: 100px;
            text-align: right;
            margin-right: 10px;
        }
        .form-input {
            width: 200px;
            height: 30px;
            border: 1px solid #ccc;
            padding: 5px;
            font-size: 14px;
        }
        input[type="submit"],
        input[type="reset"] {
            width: 100px;
            height: 30px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            margin-right: 10px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2>注册</h2>
        <form method="post" action="http://localhost:8080/webJZB_war_exploded/Register">
            <div class="form-group">
                <div class="form-group">
                    <label class="form-label" for="name">用户名：</label>
                    <input class="form-input" type="text" name="name" id="name" required>
                    <div id="name-error" class="error-message"></div>
                </div>

            </div>

            <div class="form-group">
                <label class="form-label" for="pwd">密码：</label>
                <input class="form-input" type="password" name="pwd" id="pwd" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="pwd2">确认密码：</label>
                <input class="form-input" type="password" name="pwd2" id="pwd2" required>
                <div id="pwd2-error" class="error-message"></div>
            </div>


            <div class="form-group">
                <label class="form-label" for="email">邮箱：</label>
                <input class="form-input" type="text" name="email" id="email" required>
                <div id="email-error" class="error-message"></div>
            </div>

            <div class="form-group">
                <label class="form-label" for="registration-time">注册时间：</label>
                <input class="form-input" type="text" name="registration-time" id="registration-time" readonly>
            </div>

            <input type="submit" value="注册">
            <input type="reset" value="重新输入">
        </form>
    </div>
</div>

<script>
    var registrationTimeInput = document.getElementById("registration-time");
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var currentMonth = ("0" + (currentDate.getMonth() + 1)).slice(-2);
    var currentDay = ("0" + currentDate.getDate()).slice(-2);
    var currentDateTimeString = currentYear + "-" + currentMonth + "-" + currentDay;
    registrationTimeInput.value = currentDateTimeString;

    var emailInput = document.getElementById("email");
    var emailError = document.getElementById("email-error");

    emailInput.addEventListener("input", function () {
        var email = emailInput.value;
        if (!validateEmail(email)) {
            emailError.textContent = "请输入有效的邮箱地址";
        } else {
            emailError.textContent = "";
        }
    });

    function validateEmail(email) {
        // 邮箱验证的正则表达式
        var emailPattern = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        return emailPattern.test(email);
    }


    var pwdInput = document.getElementById("pwd");
    var pwd2Input = document.getElementById("pwd2");
    var pwd2Error = document.getElementById("pwd2-error");

    pwd2Input.addEventListener("input", function () {
        var pwd = pwdInput.value;
        var pwd2 = pwd2Input.value;
        if (pwd !== pwd2) {
            pwd2Error.textContent = "确认密码与密码不一致";
        } else {
            pwd2Error.textContent = "";
        }
    });


    var nameInput = document.getElementById("name");
    var nameError = document.getElementById("name-error");

    nameInput.addEventListener("input", function () {
        var name = nameInput.value;
        if (name.length <= 4) {
            nameError.textContent = "用户名长度必须大于4位";
        } else {
            nameError.textContent = "";
        }
    });

</script>

</body>
</html>
