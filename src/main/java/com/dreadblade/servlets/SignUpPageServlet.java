package com.dreadblade.servlets;

import com.dreadblade.dao.DaoFactory;
import com.dreadblade.dao.UserDao;
import com.dreadblade.entity.User;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet creates and authenticate new users
 * And adds the attribute to the session "current_user" which contains an object of the User class
 */
@WebServlet(name = "SignUpPageServlet")
public class SignUpPageServlet extends HttpServlet {
    private static final String SIGN_UP_PAGE_PATH = "/WEB-INF/view/sign_up_page.jsp";
    private static final Logger log = Logger.getLogger(SignUpPageServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.create(req.getParameter("user_name"), req.getParameter("first_name"), req.getParameter("last_name"),
                req.getParameter("email"), req.getParameter("password"));

        HttpSession session = req.getSession();
        session.setAttribute("current_user", user);
        log.trace("User \"" + user.getUsername() + "\" and id \"" + user.getId() + "\" signed up");
        getServletContext().getRequestDispatcher("/menu").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(SIGN_UP_PAGE_PATH).forward(req, resp);
    }
}