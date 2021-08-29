package edu.epam.webproject.entity;

import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.model.dao.impl.UserDaoImpl;

public class User extends Entity {
    private long id;
    private String login;
    private String email;
    private String icon;
    private Role role;
    private UserStatus userStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                icon.equals(user.icon) &&
                role.equals(user.role) &&
                userStatus.equals(user.userStatus);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + login.hashCode()
                + email.hashCode()
                + icon.hashCode()
                + role.hashCode()
                + userStatus.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User { ");
        builder.append("id = ").append(id).append(", ");
        builder.append("login = ").append(login).append(", ");
        builder.append("email = ").append(email).append(", ");
        builder.append("icon = ").append(icon).append(", ");
        builder.append("role = ").append(role).append(", ");
        builder.append("status = ").append(userStatus).append(" ");
        builder.append("}");
        return builder.toString();
    }

    public enum UserStatus {
        APPROVED, REJECTED, IN_PROGRESS
    }

    public enum Role {
        USER, ADMIN, GUEST
    }
}
