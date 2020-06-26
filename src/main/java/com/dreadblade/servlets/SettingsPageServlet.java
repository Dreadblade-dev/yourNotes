package com.dreadblade.servlets;

import com.dreadblade.dao.DaoFactory;
import com.dreadblade.dao.NoteDao;
import com.dreadblade.dao.UserDao;
import com.dreadblade.entity.User;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SettingsPageServlet")
public class SettingsPageServlet extends HttpServlet {
    private static final String INDEX_PAGE_PATH = "/WEB-INF/view/index.jsp";
    private static final String SETTINGS_PAGE_PATH = "/WEB-INF/view/settings_page.jsp";
    private static final Logger log = Logger.getLogger(SettingsPageServlet.class.getName());
    private final UserDao dao = DaoFactory.getInstance().getUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        ServletContext context = getServletContext();

        if (action.equals("sign-out")) {
            log.trace("Trying to sign out...");
            log.trace("User \"" + user.getUsername() + "\" signed out");
            session.setAttribute("current_user", null);
            context.getRequestDispatcher(INDEX_PAGE_PATH).forward(req, resp);
        }

        /**
         * Get attribute from Settings Filter
         */
        if ((Boolean) req.getAttribute("password_correct")) {
            if (action.equals("change-username")) {
                log.trace("Trying to change username...");
                String oldUsername = user.getUsername();
                user = dao.updateById(user.getId(), req.getParameter("new_username"), user.getFirstName(),
                        user.getLastName(), user.getEmail(), user.getPassword());

                session.setAttribute("current_user", user);

                log.trace("User \"" + oldUsername + "\" changed his username to \"" +
                        user.getUsername() + "\"");
                context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
            }

            if (action.equals("change-email")) {
                log.trace("Trying to change email...");
                user = dao.updateById(user.getId(), user.getUsername(), user.getFirstName(),
                        user.getLastName(), req.getParameter("new_email"), user.getPassword());

                session.setAttribute("current_user", user);

                log.trace("User \"" + user.getUsername() + "\" changed his email to \"" +
                        user.getEmail() + "\"");
                context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
            }

            if (action.equals("change-password")) {
                log.trace("Trying to change password...");
                user = dao.updateById(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                        user.getEmail(), req.getParameter("new_password"));

                session.setAttribute("current_user", user);

                log.trace("User \"" + user.getUsername() + "\" changed his password to \"" +
                        user.getPassword() + "\"");
                context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
            }

            if (action.equals("delete-account")) {
                log.trace("Trying to delete account...");

                NoteDao noteDao = DaoFactory.getInstance().getNoteDao();
                noteDao.deleteAllByUser(user);

                dao.deleteById(user.getId());

                log.info("User \"" + user.getUsername() + "\" with id \"" + user.getId() + "\" has deleted" +
                        " his account");
                session.setAttribute("current_user", null);
                context.getRequestDispatcher(INDEX_PAGE_PATH).forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       getServletContext().getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
    }
}
