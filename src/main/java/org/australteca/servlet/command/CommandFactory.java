package org.australteca.servlet.command;

import org.australteca.servlet.command.enums.SubjectEnums;

import java.util.EnumMap;
import java.util.Map;

import static org.australteca.servlet.command.enums.SubjectEnums.*;

/**
 * Created by tomi on 12/04/17.
 */
public class CommandFactory{

    private static CommandFactory INSTANCE = new CommandFactory();
    private Map<SubjectEnums, Command> map = new EnumMap<>(SubjectEnums.class);

    private CommandFactory(){
        map.put(ADD_SUBJECT, new AddSubjectCommand());
        map.put(LIST_SUBJECT, new ListSubjectCommand());
    }

    public static CommandFactory getINSTANCE(){
        return INSTANCE;
    }

    public Command giveCommand(SubjectEnums enums){
        return map.get(enums).create();
    }
}
