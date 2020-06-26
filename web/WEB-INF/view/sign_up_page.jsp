<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 14-Jun-20
  Time: 06:31 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>Sign up</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/stylesheets/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/stylesheets/sign-up.css" type="text/css">
    </head>
    <body class="text-center">
        <form class="form-signup" action="/sign-up" method="POST">
            <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>
            <label for="inputUsername" class="sr-only">Username</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="Username" name="user_name" required maxlength="32">

            <label for="inputFirstname" class="sr-only">First name</label>
            <input type="text" id="inputFirstname" class="form-control" placeholder="First name" name="first_name" required maxlength="255">

            <label for="inputLastname" class="sr-only">Last name</label>
            <input type="text" id="inputLastname" class="form-control" placeholder="Last name" name="last_name" required maxlength="255">

            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" name="email" required maxlength="255">

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required minlength="8" maxlength="255">

            <label for="inputRepeatPassword" class="sr-only">Repeat password</label>
            <input type="password" id="inputRepeatPassword" class="form-control" placeholder="Repeat password" name="repeat_password" required minlength="8" maxlength="255">

            <button value="submit" class="btn btn-lg btn-primary btn-block">Sign up</button>
            <button form="link-to-sign-in" class="btn btn-sm btn-link" value="submit">Already have an account? Sign in</button>
        </form>
        <form id="link-to-sign-in" action="/sign-in" style="display: none" method="get"></form>
    </body>
</html>
