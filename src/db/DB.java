package db;

import bean.FoodBean;
import bean.ReservationBean;
import bean.TableBean;
import bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private static List<UserBean> users = new ArrayList<>();
    private static List<ReservationBean> reservations = new ArrayList<>();
    private static List<FoodBean> meals = new ArrayList<>();
    private static List<TableBean> tables = new ArrayList<>();
    public static UserBean session = null;


    public static List<TableBean> getTables() {
        return tables;
    }

    public static void setTables(List<TableBean> tables) {
        DB.tables = tables;
    }

    public static List<FoodBean> getMeals() {
        return meals;
    }

    public static void setMeals(List<FoodBean> meals) {
        DB.meals = meals;
    }

    public static List<UserBean> getUsers() {
        return users;
    }

    public static void setUsers(List<UserBean> users) {
        DB.users = users;
    }

    public static List<ReservationBean> getReservations() {
        return reservations;
    }

    public static void setReservations(List<ReservationBean> reservations) {
        DB.reservations = reservations;
    }
}
