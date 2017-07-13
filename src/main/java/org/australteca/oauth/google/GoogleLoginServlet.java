package org.australteca.oauth.google;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.australteca.Constants.*;


public class GoogleLoginServlet extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        resp.setContentType("text/html");
        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            String password = idToken.substring(0, 30);

            UserDao userDao = new UserDao();
            User user = userDao.getUserByEmail(email);

            if(user == null){

                String nameParam = Constants.NAME_PARAM+"="+name;
                String emailParam = Constants.EMAIL_PARAM+"="+email;
                String passwordParam = Constants.PASSWORD_PARAM+"="+password;
                String passwordConfirmationParam = Constants.PASSWORD_CONFIRMATION_PARAM+"="+password;
                String oauthParam = Constants.OAUTH_TYPE+"="+Constants.OAUTH_TYPE;
                String url = "/register?" + nameParam + "&" + emailParam + "&" + passwordParam + "&" +passwordConfirmationParam + "&" + oauthParam;
                req.getRequestDispatcher(url).forward(req, resp);
            }else{
                req.setAttribute(LOGIN_USERNAME_FIELD, email);
                req.setAttribute(LOGIN_PASSWORD_FIELD, idToken);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}