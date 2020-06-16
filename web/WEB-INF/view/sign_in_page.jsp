<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 14-Jun-20
  Time: 03:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error_page.jsp" %>
<!DOCTYPE html>
<html>
    <head lang="en-US">
        <title>Sign in</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <%--
        <style>
            <%@include file="/WEB-INF/view/stylesheets/login.css"%>
        </style>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:400" rel="stylesheet" type="text/css">
        --%>
    </head>
    <body>
        <form action="/sign-in" method="POST">
            <h1>Sign in</h1>
            <input type="text" placeholder="Username" name="user_name" required>
            <br>
            <input type="password" placeholder="Password" name="password" required>
            <br>
            <button value="submit">Sign in</button>
        </form>
    </body>
</html>