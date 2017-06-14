package org.australteca.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by tomi on 15/05/17.
 */

public class NoCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

            /*HttpServletResponse response = (HttpServletResponse) res;
            HttpServletRequest request = (HttpServletRequest) req;

            String path = request.getRequestURI().substring(request.getContextPath().length());

            String userString = request.getRemoteUser();

            if(userString == null && (path.equals("/loginForm.jsp") || path.equals("/") || path.equals("/userListPost"))){
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
                chain.doFilter(req, res);
            }else if(userString == null && !path.equals("/j_security_check")){
                response.sendRedirect("/loginForm.jsp");
            }
            else {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
                chain.doFilter(req, res);
            }*/
    }

    @Override
    public void destroy() {

    }
}
