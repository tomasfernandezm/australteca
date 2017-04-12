package org.australteca.servlet.command;

import org.australteca.entity.Subject;
import org.australteca.servlet.enums.SubjectEnums;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by tomi on 12/04/17.
 */
public class CommandFactory{

    public CommandFactory(){

    }

    public Command getCommand(SubjectEnums enums){

        Command command = null;
        // me gustaría hacerlo con un mapa
        switch (enums){
            case ADD_SUBJECT:
                command = new AddSubjectCommand();
                break;
            case LIST_SUBJECT:
                command = new ListSubjectCommand();
                break;
        }

        return command;
    }
}
