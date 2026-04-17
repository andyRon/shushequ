package top.andyron.shushequ.service.article.service;


import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.article.CategoryReq;
import top.andyron.shushequ.api.model.vo.article.SearchCategoryReq;
import top.andyron.shushequ.api.model.vo.article.dto.CategoryDTO;

/**
 * 分类后台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface CategorySettingService {

    void saveCategory(CategoryReq categoryReq);

    void deleteCategory(Integer categoryId);

    void operateCategory(Integer categoryId, Integer pushStatus);

    /**
     * 获取category列表
     *
     * @param pageParam
     * @return
     */
    PageVo<CategoryDTO> getCategoryList(SearchCategoryReq params);
}
