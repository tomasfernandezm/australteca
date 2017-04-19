package org.australteca.servlet.command.factory;

/**
 * Created by tomi on 19/04/17.
 */
public class UserCommandFactory extends AbstractCommandFactory {

    private static CommandFactory INSTANCE = new UserCommandFactory();

    private UserCommandFactory(){

    }

    public static CommandFactory getCommandFactory() {
        return INSTANCE;
    }
}
