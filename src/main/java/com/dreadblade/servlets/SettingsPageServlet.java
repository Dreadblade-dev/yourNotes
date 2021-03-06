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
    private ServletContext context;
    private HttpSession session;
    private User user;
    private boolean passwordIsCorrect;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        session = req.getSession();
        user = (User) session.getAttribute("current_user");
        context = getServletContext();

        if (action.equals("sign-out")) {
            log.trace("Trying to sign out...");
            log.trace("User \"" + user.getUsername() + "\" signed out");
            session.setAttribute("current_user", null);
            context.getRequestDispatcher(INDEX_PAGE_PATH).forward(req, resp);
        }

        session.setAttribute("account_action_failed", false);
        session.setAttribute("change_username_is_busy", false);
        session.setAttribute("change_username_password_invalid", false);
        session.setAttribute("change_email_is_busy", false);
        session.setAttribute("change_email_password_invalid", false);
        session.setAttribute("change_password_invalid", false);
        session.setAttribute("delete_account_password_invalid", false);

        /**
         * Get attribute from Settings Filter
         */
        passwordIsCorrect = (Boolean) req.getAttribute("password_correct");

        if (action.equals("change-username")) {
            changeUsername(req, resp);
        }

        if (action.equals("change-email")) {
            changeEmail(req, resp);
        }

        if (action.equals("change-password")) {
            changePassword(req, resp);
        }

        if (action.equals("delete-account")) {
            deleteAccount(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       getServletContext().getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
    }

    private void changeUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (passwordIsCorrect) {
            log.trace("Trying to change username...");

            User userWithSameUsername = dao.findByUsername(req.getParameter("new_username"));

            if (userWithSameUsername == null) {
                String oldUsername = user.getUsername();
                user = dao.updateById(user.getId(), req.getParameter("new_username"), user.getFirstName(),
                        user.getLastName(), user.getEmail(), user.getPassword());

                session.setAttribute("current_user", user);

                log.trace("User \"" + oldUsername + "\" changed his username to \"" +
                        user.getUsername() + "\"");
            } else {
                session.setAttribute("account_action_failed", true);
                session.setAttribute("change_username_is_busy", true);
                log.trace("User with same username already exists");
            }
        } else {
            session.setAttribute("account_action_failed", true);
            session.setAttribute("change_username_password_invalid", true);
        }
        context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
    }

    private void changeEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (passwordIsCorrect) {

                User userWithSameEmail = dao.findByEmail(req.getParameter("new_email"));

                if (userWithSameEmail == null) {
                    log.trace("Trying to change email...");
                    user = dao.updateById(user.getId(), user.getUsername(), user.getFirstName(),
                            user.getLastName(), req.getParameter("new_email"), user.getPassword());

                    session.setAttribute("current_user", user);

                    log.trace("User \"" + user.getUsername() + "\" changed his email to \"" +
                            user.getEmail() + "\"");
                } else {
                    session.setAttribute("account_action_failed", true);
                    session.setAttribute("change_email_is_busy", true);
                    log.trace("User with same email already exists");
                }
            } else {
                session.setAttribute("account_action_failed", true);
                session.setAttribute("change_email_password_invalid", true);
            }
        context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
    }

    private void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (passwordIsCorrect) {
            log.trace("Trying to change password...");
            user = dao.updateById(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getEmail(), req.getParameter("new_password"));

            session.setAttribute("current_user", user);

            log.trace("User \"" + user.getUsername() + "\" changed his password to \"" +
                    user.getPassword() + "\"");
            context.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
        } else {
            session.setAttribute("account_action_failed", true);
            session.setAttribute("change_password_invalid", true);
            getServletContext().getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
        }
    }

    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (passwordIsCorrect) {
            log.trace("Trying to delete account...");

            NoteDao noteDao = DaoFactory.getInstance().getNoteDao();
            noteDao.deleteAllByUser(user);

            dao.deleteById(user.getId());

            log.info("User \"" + user.getUsername() + "\" with id \"" + user.getId() + "\" has deleted" +
                    " his account");
            session.setAttribute("current_user", null);
            context.getRequestDispatcher(INDEX_PAGE_PATH).forward(req, resp);
        } else {
            session.setAttribute("account_action_failed", true);
            session.setAttribute("delete_account_password_invalid", true);
            getServletContext().getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);
        }
    }
}
