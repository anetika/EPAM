package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> signIn(String email, String password) throws DaoException;
    boolean signUp(String login, String email, String password) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    void changeUserStatusByEmail(String email, int status) throws DaoException;
    void updateUserIcon(long id, String icon) throws DaoException;
    void changeUserPasswordByEmail(String email, String password) throws DaoException;
}
