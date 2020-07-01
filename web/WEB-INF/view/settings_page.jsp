<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dreadblade.entity.User" %>
<%--
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
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
            <span class="navbar-brand">yourNotes</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse text-left" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link text-white-50" href="/">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link">
                            Settings<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class=\"nav-item\">
                        <form action="/settings?action=sign-out" method="post">
                            <button value="submit" class="btn btn-link text-white-50">Sign out</button>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
        <main role="main" class="container-fluid justify-content-center">
            <h2>Account information:</h2>

            <%
                User user = (User) session.getAttribute("current_user");

                out.println("<ul>");
                out.println("<li>Username: " + user.getUsername() + "</li>");
                out.println("<li>First name: " + user.getFirstName() + "</li>");
                out.println("<li>Last name: " + user.getLastName() + "</li>");
                out.println("<li>Email: " + user.getEmail() + "</li>");
                out.println("</ul>");
            %>

            <div>
                <form id="changeUsernameForm" action="/settings?action=change-username" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Change username:</h1>
                    <label for="newUsername" class="sr-only">New username</label>
                    <input id="newUsername" class="form-control" type="text" placeholder="New username" name="new_username" required>

                    <p id="changeUsernameIsBusy" class="text-danger" style="display: none;">Username is busy</p>

                    <label for="changeUsernamePassword" class="sr-only">Password</label>
                    <input id="changeUsernamePassword" class="form-control" type="password" placeholder="Password" name="password" required>

                    <p id="changeUsernameInvalidPassword" class="text-danger" style="display: none;">Invalid password</p>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change username</button>
                </form>
            </div>

            <div>
                <form id="changeEmailForm" action="/settings?action=change-email" method="post" class="form-change">
                    <h1 class="h3 mb-3 font-weight-normal">Change email:</h1>

                    <label for="newEmail" class="sr-only">New email</label>
                    <input id="newEmail" class="form-control" type="text" placeholder="New email" name="new_email" required>

                    <p id="changeEmailIsBusy" class="text-danger" style="display: none;">Email is busy</p>

                    <label for="changeEmailPassword" class="sr-only">Password</label>
                    <input id="changeEmailPassword" class="form-control" type="password" placeholder="Password" name="password" required>

                    <p id="changeEmailInvalidPassword" class="text-danger" style="display: none;">Invalid password</p>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change email</button>
                </form>
            </div>

            <div>
                <form id="changePasswordForm" onsubmit="changePasswordMismatch(); return false;"
                      action="/settings?action=change-password" method="post" class="form-change">

                    <h1 class="h3 mb-3 font-weight-normal">Change password:</h1>

                    <label for="newPassword" class="sr-only">New password</label>
                    <input id="newPassword" class="form-control" type="password" placeholder="New password" name="new_password" required minlength="8">

                    <label for="repeatNewPassword" class="sr-only">Repeat new password</label>
                    <input id="repeatNewPassword" class="form-control" type="password" placeholder="Repeat new password" name="repeat_new_password" required minlength="8">
                    
                    <label for="changePasswordPassword" class="sr-only">Password</label>
                    <input id="changePasswordPassword" class="form-control" type="password" placeholder="Password" name="password" required minlength="8">
                    
                    <p id="changePasswordPasswordMismatch" class="text-danger" style="display: none;">Password mismatch</p>
                    <p id="changePasswordInvalidPassword" class="text-danger" style="display: none;">Invalid password</p>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Change password</button>
                </form>
            </div>

            <div>
                <form id="deleteAccountForm" onsubmit="deleteAccountPasswordMismatch(); return false;"
                      action="/settings?action=delete-account" method="post" class="form-change">
                    
                    <h1 class="h3 mb-3 font-weight-normal">Delete account:</h1>

                    <label for="deleteAccountPassword" class="sr-only">Password</label>
                    <input id="deleteAccountPassword" class="form-control" type="password" placeholder="Password" name="password" required>

                    <label for="deleteAccountRepeatPassword" class="sr-only">Repeat password</label>
                    <input id="deleteAccountRepeatPassword" class="form-control" type="password" placeholder="Repeat password" name="repeat_password" required>

                    <p id="deleteAccountPasswordMismatch" class="text-danger" style="display: none;">Password mismatch</p>
                    <p id="deleteAccountInvalidPassword" class="text-danger" style="display: none;">Invalid password</p>

                    <button value="submit" class="btn btn-lg btn-primary btn-block">Delete account</button>
                </form>
            </div>

        </main>
        <script src="/scripts/settings.js" type="text/javascript"></script>
        <%
            if (session.getAttribute("account_action_failed") != null) {
                boolean accountActionFailed = (Boolean) session.getAttribute("account_action_failed");
                if (accountActionFailed) {
                    boolean changeUsernameIsBusy = (Boolean) session.getAttribute("change_username_is_busy");
                    boolean changeUsernamePasswordInvalid = (Boolean) session.getAttribute("change_username_password_invalid");
                    boolean changeEmailIsBusy = (Boolean) session.getAttribute("change_email_is_busy");
                    boolean changeEmailPasswordInvalid = (Boolean) session.getAttribute("change_email_password_invalid");
                    boolean changePasswordInvalid = (Boolean) session.getAttribute("change_password_invalid");
                    boolean deleteAccountPasswordInvalid = (Boolean) session.getAttribute("delete_account_password_invalid");

                    if (changeUsernameIsBusy) {
                        out.println("<script>changeUsernameIsBusy();</script>");
                    }

                    if (changeUsernamePasswordInvalid) {
                        out.println("<script>changeUsernameInvalidPassword();</script>");
                    }

                    if (changeEmailIsBusy) {
                        out.println("<script>changeEmailIsBusy();</script>");
                    }

                    if (changeEmailPasswordInvalid) {
                        out.println("<script>changeEmailInvalidPassword();</script>");
                    }

                    if (changePasswordInvalid) {
                        out.println("<script>changePasswordInvalidPassword();</script>");
                    }

                    if (deleteAccountPasswordInvalid) {
                        out.println("<script>deleteAccountInvalidPassword();</script>");
                    }
                }
            }
        %>
    </body>
</html>