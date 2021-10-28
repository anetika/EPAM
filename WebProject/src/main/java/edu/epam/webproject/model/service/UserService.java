package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface provides methods to implement business logic connected with {@link User}
 */
public interface UserService {
    /**
     * Allows user to sign in
     *
     * @param email - email
     * @param password - password
     * @return user in right case
     * @throws ServiceException when problems with execution of requests occur
     */
    Optional<User> signIn(String email, String password) throws ServiceException;

    /**
     * Allows user to sign up
     *
     * @param login - login
     * @param email - email
     * @param password - password
     * @return user in right case
     * @throws ServiceException when problems with execution of requests occur
     */
    Optional<User> signUp(String login, String email, String password) throws ServiceException;

    /**
     * Finds list of all users
     *
     * @return the list of users
     * @throws ServiceException when problems with execution of requests occur
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Activates user by email
     *
     * @param email - email
     * @throws ServiceException when problems with execution of requests occur
     */
    void activateUserByEmail(String email) throws ServiceException;

    /**
     * Changes user status by email
     *
     * @param email - email
     * @param status - user status
     * @throws ServiceException when problems with execution of requests occur
     */
    void changeUserStatusByEmail(String email, User.UserStatus status) throws ServiceException;

    /**
     * Updates user icon
     *
     * @param id - user id
     * @param icon - icon
     * @throws ServiceException when problems with execution of requests occur
     */
    void updateUserIcon(long id, String icon) throws ServiceException;

    /**
     * Changes user password by email
     *
     * @param email - email
     * @param password - password
     * @param repeatedPassword - repeated password
     * @return true if password successfully changed and false otherwise
     * @throws ServiceException when problems with execution of requests occur
     */
    boolean changeUserPasswordByEmail(String email, String password, String repeatedPassword) throws ServiceException;

    /**
     * Checks if user exists
     *
     * @param email - email
     * @return true if user exists and false otherwise
     * @throws ServiceException when problems with execution of requests occur
     */
    boolean isUserExists(String email) throws ServiceException;


    /**
     * Gets user by email.
     *
     * @param email - email
     * @return user by email
     * @throws ServiceException when problems with execution of requests occur
     */
    User getUserByEmail(String email) throws ServiceException;
}
