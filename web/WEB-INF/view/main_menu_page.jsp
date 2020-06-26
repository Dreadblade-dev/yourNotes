<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dreadblade.entity.User" %>
<%@ page import="com.dreadblade.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.ListIterator" %><%--
  Created by IntelliJ IDEA.
  User: Dreadblade-
  Date: 15-Jun-20
  Time: 06:37 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <title>yourNotes</title>
        <meta charset="utf-8">
        <meta content="text/html">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/stylesheets/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="/stylesheets/main-menu.css" type="text/css">
    </head>
    <body class="text-center">

    <%--
    Modal window for add a new note
    --%>
    <div class="modal fade" id="add-note" tabindex="-1" role="dialog" aria-labelledby="add-note-label" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="add-note-label">New note</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="add-note-form" action="/add-note" method="POST">
                        <div class="form-group">
                            <label for="add-note-title" class="col-form-label">Title:</label>
                            <input id="add-note-title"  class="form-control" name="title" type="text" required>
                        </div>
                        <div class="form-group">
                            <label for="add-note-content" class="col-form-label">Content:</label>
                            <textarea id="add-note-content" class="form-control" name="content" required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button form="add-note-form" type="submit" class="btn btn-primary">Add a new note</button>
                </div>
            </div>
        </div>
    </div>

    <%--
    Modal window for edit a note
    --%>
    <div class="modal fade" id="edit-note" tabindex="-1" role="dialog" aria-labelledby="edit-note-label" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="edit-note-label">Edit a note</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="edit-note-form" action="" method="POST">
                        <div class="form-group">
                            <label for="edit-note-title" class="col-form-label">Title:</label>
                            <input id="edit-note-title"  class="form-control" name="title" type="text" required>
                        </div>
                        <div class="form-group">
                            <label for="edit-note-content" class="col-form-label">Content:</label>
                            <textarea id="edit-note-content" class="form-control" name="content" required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button form="edit-note-form" type="submit" class="btn btn-primary">Edit a note</button>
                </div>
            </div>
        </div>
    </div>
        <%!
            private void printSignOutButton(JspWriter out) throws IOException {
                out.println("<form action=\"/settings?action=sign-out\" method=\"post\">\n" +
                        "<button value=\"submit\" class=\"btn btn-link text-white-50\">Sign out</button>\n" +
                        "</form>");
            }

            private void printAddNewNoteButton(JspWriter out) throws IOException {
                out.println("<button type=\"button\" class=\"btn btn-outline-light mr-2\" data-toggle=\"modal\"" +
                        "data-target=\"#add-note\">\n <span aria-hidden=\"true\">\n" +
                        "<span span aria-hidden=\"true\">Add a new note</span>\n</button>");
            }

            private void printSettingsButton(JspWriter out) throws IOException {
                out.println("<a href=\"/settings\" class=\"btn btn-link text-white-50\">Settings</a>");
            }

            private void printEditNoteButton(JspWriter out, Note currentNote) throws IOException {
                out.println("<button type=\"button\" class=\"close mr-2\" onclick=\"editNote(" +
                        currentNote.getId() + ", \'" + currentNote.getTitle() + "\', \'" +
                        currentNote.getContent() + "\')\">\n<span aria-hidden=\"true\">\n" +
                        "<img src=\"/assets/images/edit.svg\" alt=\"edit a note\">\n</button>");
            }

            private void printDeleteNoteButton(JspWriter out, Note currentNote) throws IOException {
                out.println("<button value=\"submit\" form=\"note_actions\" formaction=\"/delete-note?id=" +
                        currentNote.getId() + "\" class=\"close\" aria-label=\"Delete\">\n" +
                        "<span aria-hidden=\"true\">&times;</span>\n</button>");
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
                out.println("<li class=\"nav-item active\">");
                out.println("<a class=\"nav-link\">Home<span class=\"sr-only\">(current)</span></a>");
                out.println("</li>");
                out.println("<li class=\"nav-item\">");
                printSettingsButton(out);
                out.println("</li>");
                out.println("<li class=\"nav-item\">");
                printSignOutButton(out);
                out.println("</li>");
                out.println("</ul>");
                printAddNewNoteButton(out);
                out.println("</div>");
                out.println("</nav>");
            }
        %>
        <main role="main" class="container-fluid">
        <%
            User user = (User) session.getAttribute("current_user");
            @SuppressWarnings("unchecked")
            List<Note> notes = (List<Note>) session.getAttribute("current_user_notes");
            if (notes != null)
            {
                if (!notes.isEmpty()) {
                    ListIterator iterator = notes.listIterator(notes.size());
                    printNavbar(out);
                    out.println("<div class=\"row justify-content-center\">");
                    while (iterator.hasPrevious()){
                        Note currentNote = (Note) iterator.previous();
                        out.println("<div class=\"col col-12 col-md-3 mb-3 mx-2 shadow p-3 bg-white rounded-lg text-left\"");
                        out.println("<h3><strong>" + currentNote.getTitle() + "</strong></h3>");
                        printDeleteNoteButton(out, currentNote);
                        printEditNoteButton(out, currentNote);
                        out.println("<p>" +currentNote.getContent() + "</p>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                } else {
                    out.println("<h1>Welcome to yourNotes, " + user.getUsername() + "!</h1>");
                    printNavbar(out);
                    out.println("<p>What do you thinking about?</p>");
                }
            }
        %>
        </main>
        <form id="note_actions" method="POST"></form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="scripts/bootstrap.min.js"></script>
        <script src="/scripts/edit-note.js"></script>
    </body>
</html>