package org.australteca.servlet;

import com.sun.istack.internal.NotNull;
import org.australteca.Constants;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.SubjectModeratorRelationship;

import java.util.List;

/**
 * Created by tomi on 03/06/17.
 */
public class ModeratorChecker {

   boolean checkIfModerator(@NotNull String toCheck, @NotNull String subjectName){
        SubjectModeratorRelationshipDao smrd = new SubjectModeratorRelationshipDao();
        List<SubjectModeratorRelationship> smrList = smrd.getModeratorsBySubject(subjectName);

        for(SubjectModeratorRelationship smr: smrList){
            if(smr.getUser().getEmail().equals(toCheck)) return true;
        }
        return  false;
    }

    boolean checkIfAdmin(@NotNull String toCheck){
        return toCheck.equals(Constants.ADMIN_USERNAME_1) || toCheck.equals(Constants.ADMIN_USERNAME_2);
    }

    public boolean check(@NotNull String toCheck, @NotNull String subjectName){
        return checkIfModerator(toCheck, subjectName) || checkIfAdmin(toCheck);
    }
}
