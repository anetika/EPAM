package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.CustomConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT users.user_id, users.login, users.email, users.password, roles.role_type, user_statuses.status_type " +
            "FROM users JOIN roles ON users.role_id = roles.role_id JOIN user_statuses ON users.status_id = user_statuses.status_id " +
            "WHERE users.email = ?";

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
                String hashPassword = resultSet.getString(ColumnName.EMAIL);
                // TODO add password decryption
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.signIn request", e);
        }
        return Optional.ofNullable(user);
    }

    private static User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.USER_ID));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_TYPE).toUpperCase()));
        user.setUserStatus(User.UserStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase()));
        user.setIcon(resultSet.getString(ColumnName.ICON));
        return user;
    }
}
