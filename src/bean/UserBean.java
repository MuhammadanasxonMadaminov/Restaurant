package bean;

public class UserBean extends BaseIdBean{
    private String login;
    private String password;
    private Role role;

    public UserBean() {
    }

    public UserBean(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserBean(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
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
}
