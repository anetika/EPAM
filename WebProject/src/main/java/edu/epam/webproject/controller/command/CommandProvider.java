package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.go.GoToSignInPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignUpPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.GO_TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
