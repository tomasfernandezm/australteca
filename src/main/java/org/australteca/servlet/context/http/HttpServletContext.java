package org.australteca.servlet.context.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 26/04/17.
 */
public class HttpServletContext {

    private final HttpServletResponse resp;
    private final HttpServletRequest req;
    private boolean isRedirect;

    public HttpServletContext(HttpServletRequest req, HttpServletResponse resp) {
        this.resp = resp;
        this.req = req;
    }

    public void forwardRequest(String path) throws ServletException, IOException{
        req.getRequestDispatcher(path).forward(req, resp);
    }
    
    public HttpServletRequest getServletRequest() {
        return req;
    }

    public HttpServletResponse getServletResponse() {
        return resp;
    }

    public void redirect(String path) throws IOException{
        resp.sendRedirect(path);
    }
}
