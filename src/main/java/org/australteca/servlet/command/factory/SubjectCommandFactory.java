package org.australteca.servlet.command.factory;

import org.australteca.servlet.command.AddSubjectCommand;
import org.australteca.servlet.command.enums.SubjectEnums;

/**
 * Created by tomi on 19/04/17.
 */
public class SubjectCommandFactory extends AbstractCommandFactory{

    private static CommandFactory INSTANCE = new SubjectCommandFactory();

    private SubjectCommandFactory(){
        map.put(SubjectEnums.ADD_SUBJECT, new AddSubjectCommand());
    }

    public static CommandFactory getCommandFactory() {
        return INSTANCE;
    }
}
