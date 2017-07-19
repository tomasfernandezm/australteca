package org.australteca.servlet.email;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.email.EmailSender;
import org.australteca.entity.Publication;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 12/06/17.
 */
public class PublicationPetitionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();
        String publicationIDString = req.getParameter(Constants.PUBLICATION_ID);
        Integer publicationID = Integer.parseInt(publicationIDString);

        PublicationDao publicationDao = new PublicationDao();
        Publication publication = publicationDao.get(publicationID);

        String authorEmail = publication.getAuthor().getEmail();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        String toSend = user.getFirstName() + " " + user.getLastName() + " is interested in your publication ________ !. Why don't you contact him ? His contact email is: " + email;

        EmailSender emailSender = new EmailSender();
        emailSender.send(authorEmail, "Good News! Someone is interested in your publication", toSend, false);

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson("OK"));
    }
}