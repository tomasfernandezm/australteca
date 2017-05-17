package org.australteca.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 15/05/17.
 */

public class NoCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getRemoteUser() == null) {
            resp.sendRedirect(resp.encodeRedirectURL("/login.jsp"));
        } else {
            resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            resp.setDateHeader("Expires", 0); // Proxies.
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
