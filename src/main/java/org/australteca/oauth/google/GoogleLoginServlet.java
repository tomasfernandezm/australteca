package org.australteca.oauth.google;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.oauth.HttpClient;
import org.australteca.oauth.HttpParameterPair;
import org.securityfilter.authenticator.FormAuthenticator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.australteca.Constants.*;


public class GoogleLoginServlet extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = null;

            payLoad = IdTokenVerifierAndParser.getPayload(idToken);
            String name = (String) payLoad.get("name");
            String email = payLoad.getEmail();
            String password = idToken.substring(0, 30);

            List<String> response = new ArrayList<>();
            UserDao userDao = new UserDao();
            User user = userDao.getUserByEmail(email);

            response.add(email);

            if(user == null) {
                user = new User(name, "", email, "", password, false, false);
                userDao.add(user);
                response.add(password);
            }else{
                response.add(user.getPassword());
            }
            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}