package org.australteca.servlet.command.factory;

import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.enums.SubjectEnums;

/**
 * Created by tomi on 19/04/17.
 */
public interface CommandFactory {

    Command giveCommand(SubjectEnums enums);
}
