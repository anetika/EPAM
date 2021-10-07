package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String email, String password) throws ServiceException;
    Optional<User> signUp(String login, String email, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    void activateUserByEmail(String email) throws ServiceException;
    void changeUserStatusByEmail(String email, User.UserStatus status) throws ServiceException;
    void updateUserIcon(long id, String icon) throws ServiceException;
    boolean changeUserPasswordByEmail(String email, String password, String repeatedPassword) throws ServiceException;
}
