
import bean.*;
import db.DB;
import enums.UserRole;
import resource.FoodResource;
import resource.ReservationResource;
import resource.UserResource;

import java.util.Scanner;

import static db.DB.session;
import static enums.UserRole.*;

public class Main {
    static Scanner scannerNum = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static ApiResponse response;


    public static void main(String[] args) {

        showMenu();

    }


    private static void showMenu() {
        defaultAdding();


        if (session == null) {
            registerAndLogin();
        } else {
            menu();
        }
    }

    private static void menu() {
        if (session.getRole().equals(ADMIN)) {
            System.out.println("            Xush Kelibsiz");
            showStaffMenu();
        } else if (DB.session.getRole().equals(UserRole.USER)) {
            System.out.println("            Xush Kelibsiz");
            showCustomerMenu();
        }
    }

    private static void registerAndLogin() {
        System.out.println("1. register");
        System.out.println("2. login");
        System.out.print("Enter: ");
        byte choose = scannerNum.nextByte();

        switch (choose) {
            case 1 :
                register();
            case 2 :
                login();
            default: System.out.println("error");
        }
        showMenu();
    }


    private static void showCustomerMenu() {
        System.out.println("1. show restaurant details");
        System.out.println("2. show meal's menu ");
        System.out.println("3. reserve table");
        System.out.println("4. cancel reserve");
        System.out.println("5. update reserve");
        System.out.println("6. show reservation");
        System.out.println("0. Logout");
        System.out.print("Enter: ");
        int choice = scannerNum.nextInt();
        switch (choice) {
            case 1 :
                showRestaurantDetails();
            case 2 :
                showMenuItem();
            case 3 :
                reserveTable();
            case 4 :
                cancelReservation();
            case 5 :
                updateReservation();
            case 6 :
                showReservations();
            case 0 :
                logout();
        }
    }


    private static void showStaffMenu() {
        System.out.println("1. show restaurant details");
        System.out.println("2. show meal's menu ");
        System.out.println("3. reserve table");
        System.out.println("4. cancel reserve");
        System.out.println("5. update reserve");
        System.out.println("6. show reservation");
        System.out.println("7. add meal");
        System.out.println("8. delete meal");
        System.out.println("9. update meal");
        System.out.println("0. log-out");
        System.out.print("Enter: ");

        int choice = scannerNum.nextInt();
        switch (choice) {
            case 1 :
                showRestaurantDetails();
            case 2 :
                showMenuItem();
            case 3 :
                reserveTable();
            case 4 :
                cancelReservation();
            case 5 :
                updateReservation();
            case 6 :
                showReservations();
            case 7 :
                addItem();
            case 8 :
                deleteItem();
            case 9 :
                updateItem();
            case 0 :
                logout();
        }
    }


    private static void updateReservation() {
        ReservationResource reservationResource = new ReservationResource();
        System.out.print("Old Reservation id => ");
        Integer resId = scannerNum.nextInt();


        int j = 0;
        while (true) {
            ApiResponse response = reservationResource.get(j++);
            if (response.getData() != null) {
                ReservationBean r = (ReservationBean) response.getData();
                if (!r.getDeleted()) if (!session.getRole().equals(ADMIN))
                    if (r.getUserLogin().equals(session.getLogin()) && r.getId().equals(resId)) {
                        FoodResource foodResource = new FoodResource();
                        FoodBean[] foodBean = new FoodBean[10];
                        for (int i = 0; i < 10; i++) {
                            System.out.print("Enter food id => ");
                            int id = scannerNum.nextInt();
                            response = foodResource.get(id);
                            if (response.getData() != null) {
                                foodBean[i] = (FoodBean) response.getData();
                                System.out.println("1. Add meal");
                                System.out.println("2. Complete to choosing");
                                System.out.print("Enter => ");
                                int choose = scannerNum.nextInt();
                                if (choose != 1) {
                                    break;
                                }
                            } else {
                                i--;
                            }
                        }
                        System.out.print("Number of people => ");
                        int people = scannerNum.nextInt();
                        String userLogin = session.getLogin();
                        if (session.getRole().equals(ADMIN)) {
                            System.out.print("User login => ");
                            userLogin = scannerStr.next();
                        }
                        ApiResponse apiResponce = reservationResource.update(new ReservationBean(resId, people, foodBean, userLogin));

                        System.out.println(apiResponce.getMessage());
                        break;
                    }
            } else {
                break;
            }
        }
        showMenu();
    }


    private static void cancelReservation() {
        ReservationResource reservationResource = new ReservationResource();
        System.out.print("Reservation id => ");
        Integer id = scannerNum.nextInt();

        int i = 0;
        while (true) {
            ApiResponse response = reservationResource.get(i++);
            if (response.getData() != null) {
                ReservationBean r = (ReservationBean) response.getData();
                if (!r.getDeleted())
                    if (!session.getRole().equals(ADMIN)){
                        if (r.getUserLogin().equals(session.getLogin()) && r.getId().equals(id)){
                            response = reservationResource.delete(id);
                            System.out.println(response.getMessage());
                            break;
                        }
                    }else {
                        response = reservationResource.delete(id);
                        System.out.println(response.getMessage());
                        break;
                    }
            } else {
                break;
            }
        }
        showMenu();
    }


    private static void showReservations() {
        ReservationResource reservationResource = new ReservationResource();
        int i = 0;
        while (true) {
            ApiResponse response = reservationResource.get(i++);
            if (response.getData() != null) {
                ReservationBean r = (ReservationBean) response.getData();
                if (!r.getDeleted()) if (session.getRole().equals(ADMIN)) System.out.println(response.getData());
                else if (r.getUserLogin().equals(session.getLogin())) System.out.println(response.getData());
            } else {
                break;
            }
        }
        showMenu();
    }


    private static void reserveTable() {
        ReservationResource reservationResource = new ReservationResource();
        FoodResource foodResource = new FoodResource();

        FoodBean[] meals = new FoodBean[10];
        for (int i = 0; i < 10; i++) {
            System.out.print("Choose meal id => ");
            int id = scannerNum.nextInt();
            response = foodResource.get(id);
            if (response.getData() != null) {
                meals[i] = (FoodBean) response.getData();
                System.out.println("1. Add meal");
                System.out.println("2. Complete to choosing");
                System.out.print("Enter => ");
                int choose = scannerNum.nextInt();
                if (choose != 1) {
                    break;
                }
            } else {
                i--;
            }
        }
        System.out.print("Number of people => ");
        int people = scannerNum.nextInt();
        System.out.print("Table id => ");
        int table = scannerNum.nextInt();
        String userLogin = session.getLogin();
        if (session.getRole().equals(ADMIN)) {
            System.out.print("User login => ");
            userLogin = scannerStr.next();
        }

        ReservationBean reservation = new ReservationBean(people, table, meals, userLogin);
        response = reservationResource.add(reservation);
        System.out.println(response.getMessage());
        showMenu();
    }


    private static void updateItem() {
        FoodResource menuResource = new FoodResource();
        System.out.print("Old Food id => ");
        Integer id = scannerNum.nextInt();
        System.out.print("Food name  => ");
        String name = scannerStr.next();
        System.out.print("Food price => ");
        Double price = scannerNum.nextDouble();

        ApiResponse apiResponce = menuResource.update(new FoodBean(id, name, price));

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void deleteItem() {
        FoodResource menuResource = new FoodResource();
        System.out.print("Food id => ");
        Integer id = scannerNum.nextInt();

        ApiResponse apiResponce = menuResource.delete(id);

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void addItem() {
        FoodResource menuResource = new FoodResource();
        System.out.print("Food name => ");
        String name = scannerStr.next();
        System.out.print("Food price => ");
        Double price = scannerNum.nextDouble();

        ApiResponse apiResponce = menuResource.update(new FoodBean(name, price));

        System.out.println(apiResponce.getMessage());
        showMenu();
    }


    private static void showMenuItem() {
        FoodResource menuResource = new FoodResource();
        for (int i = 0; i < 10; i++) {
            ApiResponse apiResponce = menuResource.get(i);
            FoodBean meal = (FoodBean) apiResponce.getData();
            if (meal != null) if (!meal.getDeleted()) System.out.println(apiResponce.getData());
        }
        showMenu();
    }


    private static void showRestaurantDetails() {
        System.out.println("Name: OQ TEPA |   Address: CHILONZOR |  Phone: +998 88 888 8888");
        showMenu();
    }


    private static void logout() {
        session = null;
        showMenu();
    }


    private static void register() {
        UserResource usersResource = new UserResource();
        scannerStr = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scannerStr.next();
        System.out.print("Enter your login: ");
        String login = scannerStr.next();
        System.out.print("Enter your password: ");
        String password = scannerStr.next();

        ApiResponse apiResponce = usersResource.add(new UserBean(name, login, password));
        System.out.println(apiResponce.getMessage());
        session = (UserBean) apiResponce.getData();
        registerAndLogin();
    }


    private static void login() {
        UserResource usersResource = new UserResource();

        System.out.print("Login: ");
        String login = scannerStr.next();
        System.out.print("Password: ");
        String password = scannerStr.next();

        ApiResponse apiResponce = usersResource.login(new UserBean(login, password));

        System.out.println(apiResponce.getMessage());
        session = (UserBean) apiResponce.getData();

        showMenu();
    }

    private static void defaultAdding() {

//        ADD tables
        for (int i = 0; i < 10; i++) {
            TableBean table = new TableBean();
            table.setId(i);
            DB.getTables().add(table);
        }

//      Create foods
        FoodBean meal1 = new FoodBean("Besh barmoq", 50_000D);
        meal1.setId(0);
        FoodBean meal2 = new FoodBean("Osh", 100_000D);
        meal2.setId(1);
        FoodBean meal3 = new FoodBean("Donar", 120_000D);
        meal3.setId(2);
        FoodBean meal4 = new FoodBean("Tandir kabob", 200_000D);
        meal4.setId(3);
        FoodBean meal5 = new FoodBean("Sho'rva", 40_000D);
        meal5.setId(4);
        DB.getMeals().add(meal1);
        DB.getMeals().add(meal2);
        DB.getMeals().add(meal3);
        DB.getMeals().add(meal4);
        DB.getMeals().add(meal5);

//        Add Users
        UserBean user1 = new UserBean("Muhammadanas", "admin", "123");
        user1.setRole(ADMIN);
        user1.setId(0);
        UserBean user2 = new UserBean("Oybek", "user", "7777");
        user2.setId(1);
        DB.getUsers().add(user1);
        DB.getUsers().add(user2);

        ReservationBean reservation2 = new ReservationBean(1, 6, 1, new FoodBean[]{meal2}, "muhammadanas","123");

        //Bron
        FoodBean[] meals2 = new FoodBean[10];
        DB.getTables().getFirst().setReserve(true);
        meals2[0] = DB.getMeals().get(1);

    }


}
