package db;

import bean.RestaurantBean;
import bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final List<UserBean> users =new ArrayList<>();
    private static final List<RestaurantBean> tables =new ArrayList<>();
    private static final List<RestaurantBean> foods =new ArrayList<>();
    public static UserBean session=null;

    public static UserBean addUser(UserBean bean){
        if(checkUserExistByLogin(bean.getLogin())){
            return null;
        }
        bean.setId(users.size());
        users.add(bean);
        return bean;
    }

    public static UserBean getUser(UserBean bean){

        for(UserBean user:users){
            if(user.getLogin().equals(bean.getLogin())&&user.getPassword().equals(bean.getPassword())){
                return user;
            }
        }

        return null;
    }

    public static boolean checkUserExistByLogin(String login){
        for(UserBean user:users){
            if(user.getLogin().equals(login)){
                return true;
            }
        }

        return false;
    }

    public static RestaurantBean getBook(Integer id) {
        for(RestaurantBean book: tables){
            if(book.getUserId()==null&&book.getId().equals(id)){
                book.setUserId(DB.session.getId());
                return book;
            }
        }
        return null;
    }

    public static List<RestaurantBean> getAvailableBooks() {
        List<RestaurantBean> resp=new ArrayList<>();

        for(RestaurantBean book: tables){
            if(book.getUserId()==null){
                resp.add(book);
            }
        }


        return resp;
    }

    public static List<RestaurantBean> getUsersBooks() {
        List<RestaurantBean> resp=new ArrayList<>();

        for(RestaurantBean book: tables){
            if(DB.session.getId().equals(book.getUserId())){
                resp.add(book);
            }
        }

        return resp;
    }

    public static boolean returnBook(Integer bookId) {
        if(bookId<0||bookId>tables.size()){
            return false;
        }

        for (RestaurantBean book: tables){
            if(DB.session.getId().equals(book.getUserId())&&book.getId().equals(bookId)){
                book.setUserId(null);
                return true;
            }
        }

            return false;
    }
}
