package bean;

public class TableBean extends BaseIdBean {
    private Boolean isReserve = false;

    public TableBean() {
    }

    public Boolean getReserve() {
        return isReserve;
    }

    public void setReserve(Boolean reserve) {
        isReserve = reserve;
    }
}
