package com.dreadblade.servlets.filter;

import com.dreadblade.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The Settings Filter checks the correctness of the entered data
 */

@WebFilter(filterName = "Settings Filter")
public class SettingsFilter implements Filter {
    private static final String SETTINGS_PAGE_PATH = "/WEB-INF/view/settings_page.jsp";
    private static final Logger log = Logger.getLogger(SettingsFilter.class.getName());
    private static final String[] actionsRequirePassword = {
            "change-username", "change-email", "change-password", "delete-account"
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        boolean isActionNull = (action == null || action.isEmpty());
        if (isActionNull) {
            /**
             * Parameter <code>action</code> in request equals <code>null</code>
             * then forward him to settings page
             */
            request.getRequestDispatcher(SETTINGS_PAGE_PATH).forward(req, resp);

        } else if (isPasswordRequired(action) ) {
            /**
             * If password entered in form is incorrect
             * then forward him to settings page
             */
            if (!user.getPassword().equals(password)) {
                log.trace("User \"" + user.getUsername() + "\" entered wrong password");
                request.setAttribute("password_correct", false);
            } else {
                request.setAttribute("password_correct", true);
            }
        }

        /**
         * Otherwise - filter
         */
        chain.doFilter(req, resp);
    }

    private boolean isPasswordRequired(String action) {
        for (String act : actionsRequirePassword) {
            if (action.equals(act))
                return true;
        }

        return false;
    }
}
