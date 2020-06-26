<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dreadblade.entity.User" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 16-Jun-20
  Time: 08:47 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>Settings</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/stylesheets/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/stylesheets/settings.css" type="text/css">
    </head>
    <body class="text-center">
        <main role="main" class="container-fluid">
            <h2>Account information:</h2>
            <%!
                private void printSignOutButton(JspWriter out) throws IOException {
                    out.println("<form action=\"/settings?action=sign-out\" method=\"post\">\n" +
                            "<button value=\"submit\" class=\"btn btn-link text-white-50\">Sign out</button>\n" +
                            "</form>");
                }

                private void printSettingsButton(JspWriter out) throws IOException {
                    out.println("<a class=\"nav-link\">Settings<span class=\"sr-only\">(current)</span></a>");
                }

                private void printNavbar(JspWriter out) throws IOException {
                    out.println("<nav class=\"navbar navbar-expand-md navbar-dark fixed-top bg-primary\">");
                    out.println("<span class=\"navbar-brand\">yourNotes</span>");
                    out.println("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"" +
                            " data-target=\"#navbarCollapse\" aria-controls=\"navbarCollapse\"" +
                            " aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                            " <span class=\"navbar-toggler-icon\"></span>\n</button>");
                    out.println("<div class=\"collapse navbar-collapse text-left\" id=\"navbarCollapse\">");
                    out.println("<ul class=\"navbar-nav mr-auto\">");
                    out.println("<li class=\"nav-item\">");
                    out.println("<a class=\"nav-link text-white-50\" href=\"/\">Home<span class=\"sr-only\">(current)</a>");
                    out.println("</li>");
                    out.println("<li class=\"nav-item active\">");
                    printSettingsButton(out);
                    out.println("</li>");
                    out.println("<li class=\"nav-item\">");
                    printSignOutButton(out);
                    out.println("</li>");
                    out.println("</ul>");
                    out.println("</div>");
                    out.println("</nav>");
                }
            %>

            <%
                User user = (User) session.getAttribute("current_user");
                printNavbar(out);
                out.println("<ul>");
                out.println("<li>Username: " + user.getUsername() + "</li>");
                out.println("<li>First name: " + user.getFirstName() + "</li>");
                out.println("<li>Last name: " + user.getLastName() + "</li>");
                out.println("<li>Email: " + user.getEmail() + "</li>");
                out.println("</ul>");
            %>
            <div class="justify-content-center">
                <form action="/settings?action=change-username" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Change username:</h1>
                    <label for="new_username" class="sr-only">New username</label>
                    <input id="new_username" class="form-control" type="text" placeholder="New username" name="new_username" required>

                    <label for="change_username_password" class="sr-only">Password</label>
                    <input id="change_username_password" class="form-control" type="password" placeholder="Password" name="password" required>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change username</button>
                </form>
            </div>
            <div class="justify-content-center">
                <form action="/settings?action=change-email" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Change email:</h1>

                    <label for="new_email" class="sr-only">New email</label>
                    <input id="new_email" class="form-control" type="text" placeholder="New email" name="new_email" required>

                    <label for="change_email_password" class="sr-only">Password</label>
                    <input id="change_email_password" class="form-control" type="password" placeholder="Password" name="password" required>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change email</button>
                </form>
            </div>
            <div class="justify-content-center">
                <form action="/settings?action=change-password" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Change password:</h1>
                    <label for="new_password" class="sr-only">New password</label>
                    <input id="new_password" class="form-control" type="password" placeholder="New password" name="new_password" required>

                    <label for="repeat_new_password" class="sr-only">Repeat new password</label>
                    <input id="repeat_new_password" class="form-control" type="password" placeholder="Repeat new password" name="repeat_new_password" required>

                    <label for="change_password_password" class="sr-only">Password</label>
                    <input id="change_password_password" class="form-control" type="password" placeholder="Password" name="password" required>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change password</button>
                </form>
            </div>
            <div class="justify-content-center">
                <form action="/settings?action=delete-account" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Delete account:</h1>
                    <label for="delete_account_password" class="sr-only">Password</label>
                    <input id="delete_account_password" class="form-control" type="password" placeholder="Password" name="password" required>

                    <label for="delete_account_repeat_password" class="sr-only">Repeat password</label>
                    <input id="delete_account_repeat_password" class="form-control" type="password" placeholder="Repeat password" name="repeat_password" required>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Delete account</button>
                </form>
            </div>
        </main>
    </body>
</html>