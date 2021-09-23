package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.SignInCommand;
import edu.epam.webproject.controller.command.impl.SignUpCommand;
import edu.epam.webproject.controller.command.impl.admin.go.GoToAllUsersPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignInPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignUpPageCommand;
import edu.epam.webproject.controller.command.impl.user.func.ActivateAccountCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.GO_TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(CommandType.GO_TO_ALL_USERS_PAGE_COMMAND, new GoToAllUsersPageCommand());
        commands.put(CommandType.ACTIVATE_ACCOUNT_COMMAND, new ActivateAccountCommand());
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
