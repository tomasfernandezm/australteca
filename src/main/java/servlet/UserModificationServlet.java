package servlet;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 11/04/17.
 */
public class UserModificationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordC = req.getParameter("passwordC");
        String career = req.getParameter("career");

        Integer id = (Integer)req.getSession().getAttribute("id");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(id);

        user.setFirstName(name);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setCourse(career);

        userDAO.update(user);
    }
}
