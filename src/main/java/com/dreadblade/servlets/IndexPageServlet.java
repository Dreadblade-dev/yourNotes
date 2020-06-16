package com.dreadblade.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet forwards users to index page if they are not authenticated
 */
@WebServlet(name = "IndexPageServlet")
public class IndexPageServlet extends HttpServlet {
    private static final String INDEX_PAGE_PATH = "/WEB-INF/view/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(INDEX_PAGE_PATH).forward(req, resp);
    }
}
