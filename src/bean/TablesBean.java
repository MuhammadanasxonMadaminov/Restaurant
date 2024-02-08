package bean;

import java.util.ArrayList;
import java.util.List;

public class TablesBean  extends BaseIdBean {
    private Boolean isBooked;
    private Integer tableCount = 10;
    private List<TablesBean> tablesBeanList = new ArrayList<>(tableCount);
}
