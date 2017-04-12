package org.australteca.servlet.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */

public interface Command {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

    Command create();
}
