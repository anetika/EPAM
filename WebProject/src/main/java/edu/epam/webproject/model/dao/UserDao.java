package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> signIn(String email, String password) throws DaoException;
}
