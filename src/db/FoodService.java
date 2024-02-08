package db;


import bean.FoodBean;

import java.util.List;

public class FoodService {
    static List<FoodBean> meals = DB.getMeals();

    public FoodBean add(FoodBean foodBean) {
        int count = 0;
        for (int i = 0; i < meals.size(); i++) {
            if (foodBean.getName().equals(meals.get(i).getName())) {
                return null;
            } else if (!meals.get(i).getDeleted()) {
                count++;
            }
        }
        if (count < 10) {
            foodBean.setId(meals.size());
            meals.add(foodBean);
            return foodBean;
        }
        return foodBean;
    }


    public static FoodBean get(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                return meals.get(i);
            }
        }
        return null;
    }

    public static Boolean update(FoodBean newFoodBean) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(newFoodBean.getId())) {
                meals.get(i).setName(newFoodBean.getName());
                meals.get(i).setPrice(newFoodBean.getPrice());
                return true;
            }
        }
        return false;
    }


    public static Boolean delete(Integer id) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId().equals(id)) {
                meals.get(i).setDeleted(true);
                return true;
            }
        }
        return false;
    }
}
