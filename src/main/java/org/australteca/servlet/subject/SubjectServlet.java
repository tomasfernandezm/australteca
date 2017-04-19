package org.australteca.servlet.subject;

import org.australteca.servlet.command.factory.CommandFactory;
import org.australteca.servlet.command.enums.SubjectEnums;
import org.australteca.servlet.command.factory.SubjectCommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */
public class SubjectServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommandFactory subjectCommandFactory = SubjectCommandFactory.getCommandFactory();
        SubjectEnums subjectEnums = SubjectEnums.valueOf(req.getParameter("command"));
        subjectCommandFactory.giveCommand(subjectEnums).execute(req, resp);
    }
}
