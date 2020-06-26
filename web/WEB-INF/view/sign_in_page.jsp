<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 14-Jun-20
  Time: 03:58 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>Sign in</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/stylesheets/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/stylesheets/sign-in.css" type="text/css">
    </head>
    <body class="text-center">
        <form class="form-signin" action="/sign-in" method="POST">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <label for="inputUsername" class="sr-only">Username</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="Username" name="user_name" autofocus required>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
            <button value="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>
            <button form="link-to-sign-up" class="btn btn-sm btn-link" value="submit">Don't have an account? Sign up</button>
        </form>
        <form id="link-to-sign-up" action="/sign-up" style="display: none" method="get"></form>
    </body>
</html>