package com.dreadblade.servlets;

import com.dreadblade.dao.DaoFactory;
import com.dreadblade.dao.NoteDao;
import com.dreadblade.entity.Note;
import com.dreadblade.entity.User;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NoteActionsServlet", urlPatterns = {"/add-note", "/edit-note", "/delete-note"})
public class NoteActionsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(NoteActionsServlet.class.getName());
    private NoteDao dao = DaoFactory.getInstance().getNoteDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("current_user");

        boolean isAddNoteRequest = req.getRequestURI().contains("add-note");
        boolean isEditNoteRequest = req.getRequestURI().contains("edit-note");
        boolean isDeleteNoteRequest = req.getRequestURI().contains("delete-note");

        if (isAddNoteRequest) {
            log.trace("User \"" + user.getUsername() + "\" created a new note");
            dao.create(user, req.getParameter("title"), req.getParameter("content").replaceAll("(\r\n|\n)", "<br>"));
        }

        if (isEditNoteRequest) {
            int id = Integer.parseInt(req.getParameter("id"));
            Note noteToEdit = dao.findById(id);
            if (noteToEdit.getOwnerID() == user.getId()) {
                dao.updateById(id, req.getParameter("title"), req.getParameter("content").replaceAll("(\r\n|\n)", "<br>"));
                log.trace("User \"" + user.getUsername() + "\" updated note with id \"" + noteToEdit.getId() + "\".");
            }
        }

        if (isDeleteNoteRequest) {
            int id = Integer.parseInt(req.getParameter("id"));
            Note noteToDelete = dao.findById(id);
            if (noteToDelete.getOwnerID() == user.getId()) {
                dao.deleteById(id);
                log.trace("User \"" + user.getUsername() + "\" deleted note with id \"" + noteToDelete.getId() + "\".");
            }
        }

        getServletContext().getRequestDispatcher("/menu").forward(req, resp);
    }
}
