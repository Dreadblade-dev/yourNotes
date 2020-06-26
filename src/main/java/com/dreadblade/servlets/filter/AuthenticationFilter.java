package com.dreadblade.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authentication filter
 * If user is signed in and trying to sign in/sign up again -> forward him to menu
 * If is not signed in user trying to add/delete a note or get to main menu/settings page -> forward him to index page
 * etc.
 */
@WebFilter(filterName = "Authentication Filter")
public class AuthenticationFilter implements Filter {

    private HttpServletRequest httpRequest;
    private static final String[] signInRequiredURLs = {
            "/menu", "/add-note", "/delete-note", "/settings"
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        boolean isSignedIn = (session != null && session.getAttribute("current_user") != null);

        boolean isSignInPage = httpRequest.getRequestURI().contains("sign-in");
        boolean isSignUpPage = httpRequest.getRequestURI().contains("sign-up");
        boolean isIndexPage = httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/");

        if (isSignedIn && (isSignInPage || isSignUpPage || isIndexPage)) {
            /**
             * The user is already signed in and he is trying to sign in/sign up again
             */
            httpRequest.getRequestDispatcher("/menu").forward(req, resp);

        } else if (!isSignedIn && isAuthRequired()) {
            /**
             * The user is not signed in and he is trying to get to the page that requires
             * authentication
             */
            httpRequest.getRequestDispatcher("/").forward(req, resp);

        }

        chain.doFilter(req, resp);
    }

    /**
     * Checks if page require authentication
     * @return true if page require auth
     */
    private boolean isAuthRequired() {
        String requestURL = httpRequest.getRequestURL().toString();

        for (String signInRequiredURL : signInRequiredURLs) {
            if (requestURL.contains(signInRequiredURL))
                return true;
        }

        return false;
    }
}
