package resource;

import bean.ApiResponse;
import bean.FoodBean;
import db.FoodService;

public class FoodResource implements BaseCrudResource<FoodBean> {
    FoodService foodService = new FoodService();


    @Override
    public ApiResponse add(FoodBean foodBean) {
        FoodBean newMeal = foodService.add(foodBean);
        return newMeal == null ? new ApiResponse(400, "The meal is already exist", null) :
                foodBean.getId() == null ? new ApiResponse(400, "Menu has got ten item! You can not add", newMeal) :
                        new ApiResponse(200, "Success", newMeal);
    }

    @Override
    public ApiResponse get(Integer id) {
        FoodBean meal = FoodService.get(id);
        return new ApiResponse(meal == null ? 400:200,meal == null ? "meal not found" : "success",meal);
    }

    @Override
    public ApiResponse update(FoodBean newMeal) {
        Boolean isUpdate = FoodService.update(newMeal);
        return new ApiResponse(!isUpdate ? 400 : 200,!isUpdate ? "meal not found" : "updated", isUpdate);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Boolean isDelete = FoodService.delete(id);
        return new ApiResponse(!isDelete ? 400 : 200,!isDelete ? "meal id not found" : "deleted", isDelete);
    }
}
