<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 28-Jun-20
  Time: 09:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>Verification</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/stylesheets/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/stylesheets/verification.css" type="text/css">
    </head>
    <body class="text-center">
        <form class="form-verification" action="/verification" method="POST">
            <h1 class="h5 mb-3 font-weight-normal">We have sent a verification code to your email</h1>
            <h1 class="h5 mb-3 font-weight-normal">Enter the verification code from the email in the field below</h1>
            <label for="verificationCode" class="sr-only">Verification code</label>
            <input type="text" id="verificationCode" class="form-control" placeholder="Verification code" name="verification_code" required maxlength="64">
            <button value="submit" class="btn btn-lg btn-primary btn-block">Verify</button>
        </form>
    </body>
</html>
