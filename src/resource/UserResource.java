package resource;

import bean.UserBean;
import db.UserService;
import bean.ApiResponse;

public class UserResource implements BaseCrudResource<UserBean> {
    UserService userService = new UserService();

    public ApiResponse login(UserBean user) {
        UserService repository = new UserService();
        UserBean newUser = repository.login(user);
        return new ApiResponse(newUser == null ? 400 : 200,newUser == null ? "user not found" : "success", newUser);
    }


    @Override
    public ApiResponse add(UserBean user) {
        UserBean newUser = userService.add(user);
        return new ApiResponse(newUser == null ? 400 : 200,newUser == null ? "user already exist" : "successfully created", newUser);
    }

    @Override
    public ApiResponse get(Integer id) {
        return null;
    }

    @Override
    public ApiResponse update(UserBean newBean) {
        return null;
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

}
