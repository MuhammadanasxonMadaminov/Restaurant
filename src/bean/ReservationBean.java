package bean;

public class ReservationBean extends BaseIdBean {
    private Integer numberOfPeople;
    private Integer tableId;
    private FoodBean[] meals;
    private String userLogin;
    private String userPassword;
    private Boolean isDeleted = false;


    public ReservationBean(Integer numberOfPeople, Integer tableId, FoodBean[] meals, String userLogin) {
        this.numberOfPeople = numberOfPeople;
        this.tableId = tableId;
        this.meals = meals;
        this.userLogin = userLogin;

    }

    public ReservationBean(Integer id, Integer numberOfPeople, Integer tableId, FoodBean[] meals, String userLogin, String userPassword) {
        this.numberOfPeople = numberOfPeople;
        this.tableId = tableId;
        this.meals = meals;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        super.setId(id);
    }



    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public FoodBean[] getMeals() {
        return meals;
    }

    public void setMeals(FoodBean[] meals) {
        this.meals = meals;
    }


    @Override
    public String toString() {
        String meals = "";
        for (int i = 0; i < getMeals().length; i++) {
            StringBuilder s = new StringBuilder();
            if (getMeals()[i] != null) {
                meals = meals.concat(getMeals()[i].toString());
            }
        }
        return String.format("RESERVATION :-> [ id : %s | number of people : %s | table id : %s | meals : %s | isDeleted : %s ]",super.getId(),getNumberOfPeople(),getTableId(),meals,getDeleted());
    }
}
