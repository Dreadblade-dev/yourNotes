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
import java.io.PrintWriter;


/**
 * Servlet authenticates users
 * And adds the attribute to the session "current_user" which contains an object of the User class
 */
@WebServlet(name = "SignInPageServlet")
public class SignInPageServlet extends HttpServlet {
    private static final String SIGN_IN_PAGE_PATH = "/WEB-INF/view/sign_in_page.jsp";
    private static final Logger log = Logger.getLogger(SignInPageServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getUserDao();
        User user = dao.findByUsername(req.getParameter("user_name"));

        if (user.getPassword().equals(req.getParameter("password"))) {
            log.trace("User " + user.getUsername() + " signed in");
            HttpSession session = req.getSession();
            session.setAttribute("current_user", user);
            getServletContext().getRequestDispatcher("/menu").forward(req, resp);
        }
        else {
            log.trace("User " + user.getUsername() + " tried to sign in, wrong password");
            PrintWriter pw = resp.getWriter();
            pw.append("Error! Wrong password!");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(SIGN_IN_PAGE_PATH).forward(req, resp);
    }
}