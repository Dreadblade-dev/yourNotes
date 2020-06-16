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
import java.util.List;

/**
 * Servlet loads users notes and forwards them to main menu page
 */
@WebServlet(name = "MainMenuPageServlet")
public class MainMenuPageServlet extends HttpServlet {
    private static final String MAIN_MENU_PAGE_PATH = "/WEB-INF/view/main_menu_page.jsp";
    private static final Logger log = Logger.getLogger(MainMenuPageServlet.class.getName());
    private NoteDao dao = DaoFactory.getInstance().getNoteDao();;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNoteListToAttribute(req);

        getServletContext().getRequestDispatcher(MAIN_MENU_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNoteListToAttribute(req);

        getServletContext().getRequestDispatcher(MAIN_MENU_PAGE_PATH).forward(req, resp);
    }

    private void setNoteListToAttribute(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("current_user");
        List<Note> notes = dao.findAllByUser(user);
        req.getSession().setAttribute("current_user_notes", notes);
    }
}
