package org.australteca.servlet;

import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.CommandFactory;
import org.australteca.servlet.enums.SubjectEnums;

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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SubjectEnums subjectEnums = (SubjectEnums) req.getAttribute("command");
    }
}
