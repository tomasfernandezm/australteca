package org.australteca.servlet.command;

import org.australteca.servlet.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */

public interface Command {

    void execute(HttpServletContext context) throws IOException, ServletException;

    Command create();
}
