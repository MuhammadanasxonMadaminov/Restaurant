package db;

import bean.UserBean;

import java.util.List;

public class UserService {
    static List<UserBean> users = DB.getUsers();


    public UserBean add(UserBean user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.getPassword().equals(users.get(i).getPassword()) &&
                    user.getLogin().equals(users.get(i).getLogin())) {
                return null;
            }
        }
        user.setId(users.size());
        users.add(user);
        return user;
    }


    public UserBean get(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }


    public UserBean login(UserBean user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(user.getLogin()) &&
                    users.get(i).getPassword().equals(user.getPassword())) {
                return users.get(i);
            }
        }
        return null;
    }
}
