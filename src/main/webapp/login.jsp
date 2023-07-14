<%--
  Created by IntelliJ IDEA.
  User: 80739
  Date: 2023/6/6
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 登录页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>

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
        .register-link {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2>登录</h2>
        <form method="post" action="http://localhost:8080/webJZB_war_exploded/Login">
            <div class="form-group">
                <label class="form-label" for="name">用户名：</label>
                <input class="form-input" type="text" name="name" id="name" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="pwd">密码：</label>
                <input class="form-input" type="password" name="pwd" id="pwd" required>
            </div>

            <div class="form-group">
                <label class="form-label" for="captcha">验证码：</label>
                <input class="form-input" type="text" name="captcha" id="captcha" required>
                <img src="CaptchaServlet" alt="验证码">
            </div>

            <input type="submit" value="登录">
            <input type="reset" value="重新输入">
        </form>
        <div class="register-link">
            <span>还没有账号？</span><a href="http://localhost:8080/webJZB_war_exploded/Register.jsp">立即注册</a>
        </div>
    </div>
</div>
</body>
</html>
