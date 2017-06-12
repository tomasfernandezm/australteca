package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by tomi on 29/04/17.
 */
public class UserPostPhotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userEmail = req.getParameter(Constants.USER_EMAIL_PARAM);
        if(userEmail != null) {

            UserDao userDao = new UserDao();
            User user = userDao.getUserByEmail(userEmail);

            if(user != null && user.getPhoto() != null) {
                resp.setHeader("Content-Disposition", "attachment; filename=\"" + "profilePicture" + "." + user.getPhoto().getExt() + "\";");
                resp.setContentType(user.getPhoto().getFormat());
                ByteArrayInputStream byteStream = new ByteArrayInputStream(user.getPhoto().getData());

                // input stream con un buffer incorporado
                BufferedInputStream bufStream = new BufferedInputStream(byteStream);
                ServletOutputStream responseOutputStream = resp.getOutputStream();
                int data = bufStream.read();
                while (data != -1) {
                    responseOutputStream.write(data);
                    data = bufStream.read();
                }
                bufStream.close();
                responseOutputStream.close();
            }
        }
    }
}
