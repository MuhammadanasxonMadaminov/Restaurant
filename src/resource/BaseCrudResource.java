package resource;

import bean.BaseIdBean;
import bean.ApiResponse;

public interface BaseCrudResource<T extends BaseIdBean> {
    ApiResponse add(T bean);

    ApiResponse get(Integer id);

    ApiResponse update(T newBean);

    ApiResponse delete(Integer id);
}