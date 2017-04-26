package org.australteca.servlet.httpcontext;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 26/04/17.
 */
public class Context implements HttpContext{

    private final HttpServletResponse resp;
    private final HttpServletRequest req;
    private boolean isRedirect;

    public Context(HttpServletResponse resp, HttpServletRequest req) {
        this.resp = resp;
        this.req = req;
    }

    @Override
    public void forwardRequest(String path) throws ServletException, IOException{
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    public HttpServletRequest getServletRequest() {
        return req;
    }

    @Override
    public HttpServletResponse getServletResponse() {
        return resp;
    }

    @Override
    public void redirect(String path) throws IOException{
        resp.sendRedirect(path);
    }
}
