package bean;

public class FoodBean extends BaseIdBean {
    private String name;
    private Double price;
    private Boolean isDeleted = false;


    public FoodBean(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    //This constructor is for update item
    public FoodBean(Integer id, String name, Double price) {
        this.name = name;
        this.price = price;
        super.setId(id);
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format("%s :-> [ food id : %s | food price : %s ]",getName(),getId(),getPrice());
    }
}
