<%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 14-Jun-20
  Time: 03:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head lang="en-US">
    <title>yourNotes</title>
    <meta charset="utf-8">
    <meta content="text/html">
    <%--
    <style>
      <%@include file="/WEB-INF/view/stylesheets/index.css"%>
    </style>
    --%>
  </head>
  <body>
  <form>
    <h1>Welcome to yourNotes!</h1>
    <button type="submit" formaction="/sign-in" formmethod="get">Sign in</button>
    <button type="submit" formaction="/sign-up" formmethod="get">Sign up</button>
  </form>
  </body>
</html>