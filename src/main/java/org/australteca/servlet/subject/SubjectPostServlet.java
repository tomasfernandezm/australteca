package org.australteca.servlet.subject;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.australteca.Constants;
import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 13/04/17.
 */
public class SubjectPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        String email = req.getRemoteUser();

        SubjectModeratorRelationshipDao smrd = new SubjectModeratorRelationshipDao();
        SubjectModeratorRelationship smr = smrd.getByUserEmailAndSubjectName(email, subjectName);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        boolean isModerator = false;
        boolean isWannabeModerator = false;

        if(smr != null && smr.isAccepted()) isModerator = true;
        else if(smr != null) isWannabeModerator = true;

        SubjectDao subjectDAO = new SubjectDao();
        Subject subject = subjectDAO.getByName(subjectName);

        ProfessorDao professorDao = new ProfessorDao();
        List<Professor> professors = professorDao.list();
        List<Professor> professorsInSubject = subject.getProfessors();

        List<ProfessorWrapper> professorWrappers = new ArrayList<>();
        for(Professor p: professors){
            if(professorsInSubject.contains(p)) professorWrappers.add(new ProfessorWrapper(p, true));
            else professorWrappers.add(new ProfessorWrapper(p, false));
        }

        Integer userScore = user.getSubjectScores().getOrDefault(subjectName, 0);
        req.setAttribute(SUBJECT_USER_SCORE, userScore);

        req.setAttribute(SUBJECT_COMMENTARY_LIST, subject.getCommentaryList());
        req.setAttribute(SUBJECT_PROFESSOR_WRAPPER_LIST, professorWrappers);
        req.setAttribute(SUBJECT_NOTES_LIST, subject.getNoteList());
        req.setAttribute(Constants.MODERATOR_PARAM, isModerator);
        req.setAttribute(Constants.WANNABE_MODERATOR_PARAM, isWannabeModerator);

        req.setAttribute(SUBJECT_NAME_PARAM, subjectName);
        req.setAttribute(SUBJECT_SCORE, new DecimalFormat("#.##").format(subject.getScore()));
        req.getRequestDispatcher("/mainMenu/subjectExample.jsp").forward(req, resp);

    }

    public class ProfessorWrapper{

        Professor professor;
        boolean favorite;

        ProfessorWrapper(Professor professor, boolean favorite) {
            this.professor = professor;
            this.favorite = favorite;
        }

        public Professor getProfessor() {
            return professor;
        }

        public boolean isFavorite() {
            return favorite;
        }
    }
}
