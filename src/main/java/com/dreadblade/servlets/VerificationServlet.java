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

@WebServlet(name = "VerificationServlet")
public class VerificationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(SignUpPageServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user_temp");
        session.removeAttribute("user_temp");
        int hash = user.hashCode();
        int enteredHash = Integer.parseInt(req.getParameter("verification_code"));
        if (hash == enteredHash) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            user = userDao.create(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getPassword());

            session.setAttribute("current_user", user);
            log.trace("User \"" + user.getUsername() + "\" and id \"" + user.getId() + "\" signed up");
            getServletContext().getRequestDispatcher("/menu").forward(req, resp);
        }
    }
}
