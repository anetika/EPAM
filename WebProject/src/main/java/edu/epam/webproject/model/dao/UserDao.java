package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface provides methods to interact with {@link User} data from database
 */
public interface UserDao {

    /**
     * Checks if database contains {@link User} with specified parameters
     *
     * @param email    - user email
     * @param password - user password
     * @return {@link Optional<User>} which contains {@link User} if there is such a user in database, {@link null} otherwise
     * @throws DaoException when problems with database connection occur
     */
    Optional<User> signIn(String email, String password) throws DaoException;

    /**
     * Adds new {@link User} with {@link User.UserStatus#IN_PROGRESS} status and with {@link User.Role#USER} role to database with specified parameters
     *
     * @param login    the login
     * @param email    - new user email
     * @param password - new user password
     * @return true if user was added to database, false if database contains user with the same email
     * @throws DaoException when problems with database connection occur
     */
    boolean signUp(String login, String email, String password) throws DaoException;

    /**
     * Finds a list of all {@link User}
     *
     * @return the list of users
     * @throws DaoException when problems with database connection occur
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Changes {@link User} status using email
     *
     * @param email  - user email
     * @param status - new user status
     * @throws DaoException when problems with database connection occur
     */
    void changeUserStatusByEmail(String email, int status) throws DaoException;

    /**
     * Updates {@link User} icon
     *
     * @param id   - user id
     * @param icon - icon
     * @throws DaoException when problems with database connection occur
     */
    void updateUserIcon(long id, String icon) throws DaoException;

    /**
     * Changes {@link User} password using email
     *
     * @param email - user email
     * @param password - new user password
     * @throws DaoException when problems with database connection occur
     */
    void changeUserPasswordByEmail(String email, String password) throws DaoException;

    /**
     * Checks {@link User} existence
     *
     * @param email - user email
     * @return user if exists
     * @throws DaoException when problems with database connection occur
     */
    Optional<User> isUserExists(String email) throws DaoException;


    /**
     * Gets user by email.
     *
     * @param email - email
     * @return user by email
     * @throws DaoException when problems with database connection occur
     */
    User getUserByEmail(String email) throws DaoException;
}
