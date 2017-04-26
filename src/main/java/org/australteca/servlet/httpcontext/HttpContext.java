package org.australteca.servlet.httpcontext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 26/04/17.
 */
public interface HttpContext {

    void forwardRequest(String path) throws ServletException, IOException;
    HttpServletRequest getServletRequest();
    HttpServletResponse getServletResponse();
    void redirect(String path) throws IOException;
}
