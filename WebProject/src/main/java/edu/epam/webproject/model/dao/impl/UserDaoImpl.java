package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.CustomConnectionPool;
import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.util.PasswordEncryptor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT users.user_id, users.login, users.email, users.icon, users.password, roles.role, user_statuses.status " +
            "FROM users JOIN roles ON users.role_id = roles.role_id JOIN user_statuses ON users.status_id = user_statuses.status_id " +
            "WHERE users.email = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO users (login, password, email, role_id, status_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_USERS_SQL = "SELECT users.user_id, users.login, users.email, users.icon, users.password, roles.role, user_statuses.status " +
            "FROM users JOIN roles ON users.role_id = roles.role_id JOIN user_statuses ON users.status_id = user_statuses.status_id WHERE users.role_id <> 1";
    private static final String CHANGE_USER_STATUS_BY_EMAIL_SQL = "UPDATE users SET status_id = ? WHERE email = ?";
    private static final String UPDATE_USER_ICON_BY_ID_SQL = "UPDATE users SET icon = ? WHERE user_id = ?";
    private static final String CHANGE_USER_PASSWORD_BY_EMAIL_SQL = "UPDATE users SET password = ? WHERE email = ?";
    private UserDaoImpl(){}

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> signIn(String email, String password) throws DaoException {
        User user = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL))  {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                String hashPassword = resultSet.getString(ColumnName.PASSWORD);
                if (encryptor.checkPassword(password, hashPassword)){
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.signIn request", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean signUp(String login, String email, String password) throws DaoException {
        final int DUPLICATE_EMAIL_ERROR_CODE = 1062;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
            statement.setString(SignUpParameterIndex.LOGIN, login);
            statement.setString(SignUpParameterIndex.PASSWORD, password);
            statement.setString(SignUpParameterIndex.EMAIL, email);
            statement.setInt(SignUpParameterIndex.ROLE, User.Role.USER.getValue());
            statement.setInt(SignUpParameterIndex.STATUS, User.UserStatus.IN_PROGRESS.getValue());
            statement.execute();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_EMAIL_ERROR_CODE) {
                return false;
            } else {
                throw new DaoException("Unable to handle UserDao.signUp request", e);
            }
        }
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL);
             while (resultSet.next()){
                 userList.add(createUser(resultSet));
             }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findAllUsers", e);
        }
        return userList;
    }

    @Override
    public void changeUserStatusByEmail(String email, int status) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_USER_STATUS_BY_EMAIL_SQL)) {
            statement.setInt(1, status);
            statement.setString(2, email);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.activateAccountByEmail", e);
        }
    }

    @Override
    public void updateUserIcon(long id, String icon) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ICON_BY_ID_SQL)){
            statement.setString(1, icon);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.updateUserIcon", e);
        }
    }

    @Override
    public void changeUserPasswordByEmail(String email, String password) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD_BY_EMAIL_SQL)) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.changeUserPasswordByEmail", e);
        }
    }


    private static User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.USER_ID));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
        user.setUserStatus(User.UserStatus.valueOf(resultSet.getString(ColumnName.STATUS).toUpperCase()));
        user.setIcon(resultSet.getString(ColumnName.ICON));
        return user;
    }

    private static class SignUpParameterIndex{
        private static final int LOGIN = 1;
        private static final int PASSWORD = 2;
        private static final int EMAIL = 3;
        private static final int ROLE = 4;
        private static final int STATUS = 5;
    }
}
