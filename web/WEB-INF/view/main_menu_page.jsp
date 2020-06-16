<%@ page import="com.dreadblade.entity.User" %>
<%@ page import="com.dreadblade.entity.Note" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 15-Jun-20
  Time: 06:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>yourNotes</title>
        <meta charset="utf-8">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("current_user");
            @SuppressWarnings("unchecked")
            List<Note> notes = (List<Note>) session.getAttribute("current_user_notes");
            if (notes != null)
            {
                if (!notes.isEmpty()) {
                    out.println("<h1>Welcome back, " + user.getUsername() + "!</h1><hr>");
                    for (Note note : notes) {
                        out.println("<ul>");
                        out.println("<li> " + note.getTitle() + "</li>");
                        out.println("<li> " + note.getContent() + "</li>");
                        out.println("<button value=\"submit\" form=\"delete\" formaction=\"/delete-note?id=" +
                                note.getId() + "\" formmethod=\"post\">Delete</button>");
                        out.println("</ul>");
                        out.println("<hr>");
                    }
                } else {
                    out.println("<h1>Welcome back, " + user.getUsername() + "!</h1>");
                    out.println("<p>What do you thinking about?</p>");
                }
            }
            else {
                out.println("<h1>Welcome to yourNotes, " + user.getUsername() + "!</h1>");
                out.println("<p>What do you thinking about?</p>");
            }
        %>

        <form action="/add-note" method="get">
            <button type="submit">Add a new note</button>
        </form>
        <form action="/settings" method="get">
            <button type="submit">Settings</button>
        </form>

        <form id="delete"></form>
    </body>
</html>