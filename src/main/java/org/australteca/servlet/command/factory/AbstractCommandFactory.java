package org.australteca.servlet.command.factory;

import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.enums.SubjectEnums;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by tomi on 12/04/17.
 */
public abstract class AbstractCommandFactory implements CommandFactory {

    Map<SubjectEnums, Command> map = new EnumMap<>(SubjectEnums.class);

    public Command giveCommand(SubjectEnums enums){
        return map.get(enums).create();
    }
}
