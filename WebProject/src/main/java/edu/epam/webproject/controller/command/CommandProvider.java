package edu.epam.webproject.controller.command;

import edu.epam.webproject.controller.command.impl.*;
import edu.epam.webproject.controller.command.impl.admin.func.ChangeUserStatusCommand;
import edu.epam.webproject.controller.command.impl.admin.func.ChangeVacancyStatusCommand;
import edu.epam.webproject.controller.command.impl.admin.go.*;
import edu.epam.webproject.controller.command.impl.go.GoToChangeUserPasswordPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToForgerPasswordPage;
import edu.epam.webproject.controller.command.impl.go.GoToSignInPageCommand;
import edu.epam.webproject.controller.command.impl.go.GoToSignUpPageCommand;
import edu.epam.webproject.controller.command.impl.user.func.ActivateAccountCommand;
import edu.epam.webproject.controller.command.impl.user.func.AddNewFeedbackCommand;
import edu.epam.webproject.controller.command.impl.user.func.AddNewVacancyCommand;
import edu.epam.webproject.controller.command.impl.user.go.GoToFindJobPageCommand;
import edu.epam.webproject.controller.command.impl.user.go.GoToOfferVacancyPageCommand;
import edu.epam.webproject.controller.command.impl.user.go.GoToUserAccountPageCommand;
import edu.epam.webproject.controller.command.impl.user.go.GoToWriteFeedbackPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.GO_TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
        commands.put(CommandType.GO_TO_USER_ACCOUNT_PAGE_COMMAND, new GoToUserAccountPageCommand());
        commands.put(CommandType.GO_TO_ADMIN_ACCOUNT_PAGE_COMMAND, new GoToAdminAccountPageCommand());
        commands.put(CommandType.GO_TO_ALL_USERS_PAGE_COMMAND, new GoToAllUsersPageCommand());
        commands.put(CommandType.GO_TO_ALL_RELEVANT_VACANCIES_PAGE_COMMAND, new GoToAllRelevantVacanciesPageCommand());
        commands.put(CommandType.GO_TO_ALL_IRRELEVANT_VACANCIES_PAGE_COMMAND, new GoToAllIrrelevantVacanciesPageCommand());
        commands.put(CommandType.GO_TO_ALL_FEEDBACKS_PAGE_COMMAND, new GoToAllFeedbacksPageCommand());
        commands.put(CommandType.GO_TO_OFFER_VACANCY_PAGE_COMMAND, new GoToOfferVacancyPageCommand());
        commands.put(CommandType.GO_TO_FIND_JOB_PAGE_COMMAND, new GoToFindJobPageCommand());
        commands.put(CommandType.GO_TO_WRITE_FEEDBACK_COMMAND, new GoToWriteFeedbackPageCommand());
        commands.put(CommandType.GO_TO_CHANGE_USER_PASSWORD_PAGE_COMMAND, new GoToChangeUserPasswordPageCommand());
        commands.put(CommandType.GO_TO_FORGET_PASSWORD_PAGE_COMMAND, new GoToForgerPasswordPage());

        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.SIGN_UP_COMMAND, new SignUpCommand());
        commands.put(CommandType.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandType.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(CommandType.ACTIVATE_ACCOUNT_COMMAND, new ActivateAccountCommand());
        commands.put(CommandType.CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(CommandType.CHANGE_VACANCY_STATUS_COMMAND, new ChangeVacancyStatusCommand());
        commands.put(CommandType.UPLOAD_USER_ICON_COMMAND, new UploadUserIconCommand());
        commands.put(CommandType.CHANGE_USER_PASSWORD_COMMAND, new ChangeUserPasswordCommand());
        commands.put(CommandType.ADD_NEW_VACANCY_COMMAND, new AddNewVacancyCommand());
        commands.put(CommandType.ADD_NEW_FEEDBACK_COMMAND, new AddNewFeedbackCommand());
        commands.put(CommandType.FORGET_PASSWORD_COMMAND, new ForgetPasswordCommand());
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
