package org.australteca.servlet.command;

import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */

public interface Command {

    void execute(HttpContext context) throws IOException, ServletException;

    Command create();
}
