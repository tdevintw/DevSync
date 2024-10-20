<%@ page import="dev.yassiraitelghari.utils.RegisterValidationMessages" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% RegisterValidationMessages validation = (RegisterValidationMessages) request.getAttribute("registerValidation");%>
<!DOCTYPE html>
<html>
<head>
    <!-- Design by foolishdeveloper.com -->
    <title>Register Your Account</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <!--Stylesheet-->
    <style media="screen">
        *,
        *:before,
        *:after {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }

        body {
            background-color: #080710;
        }

        .background {
            width: 430px;
            height: 520px;
            position: absolute;
            transform: translate(-50%, -50%);
            left: 50%;
            top: 50%;
        }

        .background .shape {
            height: 200px;
            width: 200px;
            position: absolute;
            border-radius: 50%;
        }

        .shape:first-child {
            background: linear-gradient(
                    #1845ad,
                    #23a2f6
            );
            left: -80px;
            top: -80px;
        }

        .shape:last-child {
            background: linear-gradient(
                    to right,
                    #ff512f,
                    #f09819
            );
            right: -30px;
            bottom: -80px;
        }

        form {
            margin-top: 5rem;
            width: 400px;
            background-color: rgba(255, 255, 255, 0.13);
            position: absolute;
            transform: translate(-50%, -50%);
            top: 50%;
            left: 50%;
            border-radius: 10px;
            backdrop-filter: blur(10px);
            border: 2px solid rgba(255, 255, 255, 0.1);
            box-shadow: 0 0 40px rgba(8, 7, 16, 0.6);
            padding: 50px 35px;
        }

        form * {
            font-family: 'Poppins', sans-serif;
            color: #ffffff;
            letter-spacing: 0.5px;
            outline: none;
            border: none;
        }

        form h3 {
            font-size: 32px;
            font-weight: 500;
            line-height: 42px;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 30px;
            font-size: 16px;
            font-weight: 500;
        }

        input[type="text"], input[type="email"] {
            display: block;
            height: 50px;
            width: 100%;
            background-color: rgba(255, 255, 255, 0.07);
            border-radius: 3px;
            padding: 0 10px;
            margin-top: 8px;
            font-size: 14px;
            font-weight: 300;
        }

        ::placeholder {
            color: #e5e5e5;
        }

        button {
            margin-top: 50px;
            width: 100%;
            background-color: #ffffff;
            color: #080710;
            padding: 15px 0;
            font-size: 18px;
            font-weight: 600;
            border-radius: 5px;
            cursor: pointer;
        }

        .social {
            margin-top: 30px;
            display: flex;
        }

        .social div {
            background: red;
            width: 150px;
            border-radius: 3px;
            padding: 5px 10px 10px 5px;
            background-color: rgba(255, 255, 255, 0.27);
            color: #eaf0fb;
            text-align: center;
        }

        .social div:hover {
            background-color: rgba(255, 255, 255, 0.47);
        }

        .social .fb {
            margin-left: 25px;
        }

        .social i {
            margin-right: 4px;
        }

        .checkbox-group {
            margin-top: 30px;
            display: flex;
            justify-content: space-between;
        }

        .checkbox-group label {
            font-size: 16px;
            font-weight: 500;
        }

        .checkbox-group input[type="checkbox"] {
            width: 20px;
            height: 20px;
        }
    </style>
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form action="register" method="post">
    <h3>Register Here</h3>

    <label for="username">UserName</label>
    <input name="username" type="text" placeholder="username" id="username" required>
    <%
        if (validation!=null && validation.getUsernameValidationMessage() != null)
            out.println("<p>" + validation.getUsernameValidationMessage() + "</p>");
    %>
    <label for="email">Email</label>
    <input name="email" type="text" placeholder="Email" id="email" required>
    <%
        if (validation!=null && validation.getEmailValidationMessage() != null)
            out.println("<p>" + validation.getEmailValidationMessage() + "</p>");
    %>

    <label for="password">Password</label>
    <input name="password" type="text" placeholder="password" id="password" required>
    <%
        if (validation!=null && validation.getPasswordValidationMessage() != null)
            out.println("<p>" + validation.getPasswordValidationMessage() + "</p>");
    %>

    <label for="first_name">First Name</label>
    <input name="first_name" type="text" placeholder="first name" id="first_name" required>
    <%
        if (validation!=null && validation.getFirstNameValidationMessage() != null)
            out.println("<p>" + validation.getFirstNameValidationMessage() + "</p>");
    %>

    <label for="last_name">Last Name</label>
    <input name="last_name" type="text" placeholder="last name" id="last_name" required>
    <%
        if (validation!=null && validation.getLastNameValidationMessage() != null)
            out.println("<p>" + validation.getLastNameValidationMessage() + "</p>");
    %>

    <div class="checkbox-group">
        <label style="margin-top: 0" for="manager">Manager</label>
        <input style="cursor: pointer;width: 20px" type="radio" id="manager" name="role" value="MANAGER" required>
        <label style="margin-top: 0" for="client">Client</label>
        <input style="cursor: pointer;width: 20px" type="radio" id="client" name="role" value="CLIENT" required>
    </div>
    <%
        if (validation!=null && validation.getRoleValidationMessage() != null)
            out.println("<p>" + validation.getRoleValidationMessage() + "</p>");
    %>

    <button type="submit">Register</button>
    <div class="social">
        <div class="go" style="width: 100% ; text-decoration: none"><a href="login">Already have account ?</a></div>
    </div>
</form>
</body>
</html>
