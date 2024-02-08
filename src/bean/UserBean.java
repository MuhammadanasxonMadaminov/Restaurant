package bean;

import enums.UserRole;

public class UserBean extends BaseIdBean {
    private String name;
    private String login;
    private String password;
    private UserRole role = UserRole.USER;


    public UserBean(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserBean(String name, String login, String password) {
        this.login = login;
        this.password = password;
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
