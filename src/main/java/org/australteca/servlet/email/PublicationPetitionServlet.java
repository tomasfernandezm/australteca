package org.australteca.servlet.email;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.email.EmailSender;
import org.australteca.email.HTMLParser;
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

        String publicationIDString = req.getParameter(Constants.PUBLICATION_ID);

        String emailTopic = req.getParameter(Constants.EMAIL_TOPIC);
        String emailDescription = req.getParameter(Constants.EMAIL_DESCRIPTION);
        Integer publicationID = Integer.parseInt(publicationIDString);

        PublicationDao publicationDao = new PublicationDao();
        Publication publication = publicationDao.get(publicationID);

        String authorEmail = publication.getAuthor().getEmail();

        UserDao userDao = new UserDao();
        User author = userDao.getUserByEmail(authorEmail);

        String authorName = author.getFirstName() + " " + author.getLastName();

        User sender = userDao.getUserByEmail(req.getRemoteUser());
        String senderName = sender.getFirstName() + " " + sender.getLastName();

        final String filePath = "/home/tomi/projects/australteca/src/main/webapp/html/EmailTemplate.html";

        String message = emailTopic + "\n\n" + emailDescription;

        HTMLParser htmlParser = new HTMLParser();
        String parsedHTML = htmlParser.parse(filePath);
        parsedHTML = htmlParser.setParameters(parsedHTML, authorName, senderName, publication.getName(), message);

        EmailSender emailSender = new EmailSender();
        emailSender.send(author.getEmail(), "Australteca - Alguien está interesado en tu publicación", parsedHTML, true);

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson("OK"));
    }
}