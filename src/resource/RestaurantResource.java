package resource;

import bean.ApiResponce;
import bean.RestaurantBean;
import db.DB;

import java.util.List;

public class RestaurantResource implements BaseCrudResource<RestaurantBean> {
    @Override
    public ApiResponce create(RestaurantBean bean) {
        return null;
    }

    @Override
    public ApiResponce get(Integer id) {
        RestaurantBean book=DB.getBook(id);

        if(book==null){
            return new ApiResponce(400,"book not found",null);
        }else {
            return new ApiResponce(200,"book found",book);
        }

    }

    @Override
    public ApiResponce update(RestaurantBean bean) {
        return null;
    }

    @Override
    public ApiResponce delete(Integer id) {
        return null;
    }

    public ApiResponce getAvailableBooks() {
        List<RestaurantBean> availableBooks = DB.getAvailableBooks();

        return new ApiResponce(200,"Success",availableBooks);
    }

    public ApiResponce getUsersBooks() {
        List<RestaurantBean> usersBooks = DB.getUsersBooks();

        return new ApiResponce(200,"Success",usersBooks);
    }

    public ApiResponce returnBook(Integer bookId) {
        boolean b = DB.returnBook(bookId);

        return new ApiResponce(b?200:400,b?"success":"error",b);
    }
}
