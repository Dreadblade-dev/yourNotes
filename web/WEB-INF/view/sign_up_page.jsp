<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 14-Jun-20
  Time: 06:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error_page.jsp" %>
<html>
    <head lang="">
        <title>Sign in</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <%--
        <style>
            <%@include file="/WEB-INF/view/stylesheets/sign_up_page.css"%>
        </style>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400" rel="stylesheet" type="text/css">
        --%>
    </head>
    <body>
        <form action="/sign-up" method="POST">
            <h1>Sign up</h1>
            <input type="text" placeholder="Username" name="user_name" required maxlength="32">
            <br>
            <input type="text" placeholder="First name" name="first_name" required maxlength="255">
            <br>
            <input type="text" placeholder="Last name" name="last_name" required maxlength="255">
            <br>
            <input type="email" placeholder="E-mail" name="email" required maxlength="255">
            <br>
            <input type="password" placeholder="Password" name="password" required maxlength="255">
            <br>
            <input type="password" placeholder="Repeat password" name="rPassword" required maxlength="255">
            <br>
            <button type="submit">Sign up</button>
        </form>
    </body>
</html>
