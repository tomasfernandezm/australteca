package org.australteca.servlet.command.factory;

import org.australteca.servlet.command.enums.SubjectEnums;
import org.australteca.servlet.command.subject.*;

import static org.australteca.servlet.command.enums.SubjectEnums.*;

/**
 * Created by tomi on 19/04/17.
 */
public class SubjectCommandFactory extends AbstractCommandFactory{

    private static CommandFactory INSTANCE = new SubjectCommandFactory();

    private SubjectCommandFactory(){
        map.put(ADD_SUBJECT, new AddSubjectCommand());
        map.put(ADD_PROFESSOR, new AddProfessorToSubjectCommand());
        map.put(ADD_COMMENTARY, new AddCommentaryToSubjectCommand());
        map.put(LIST_SUBJECTS, new ListSubjectCommand());
        map.put(REMOVE_SUBJECT, new DeleteSubjectCommand());
        map.put(POST_SUBJECT, new PostSubjectCommand());
    }

    public static CommandFactory getCommandFactory() {
        return INSTANCE;
    }
}
