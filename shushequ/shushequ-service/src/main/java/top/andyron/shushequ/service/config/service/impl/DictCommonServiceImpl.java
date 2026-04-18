package top.andyron.shushequ.service.config.service.impl;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.vo.article.dto.CategoryDTO;
import top.andyron.shushequ.api.model.vo.article.dto.DictCommonDTO;
import top.andyron.shushequ.service.article.service.CategoryService;
import top.andyron.shushequ.service.config.repository.dao.DictCommonDao;
import top.andyron.shushequ.service.config.service.DictCommonService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典Service
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class DictCommonServiceImpl implements DictCommonService {

    @Resource
    private DictCommonDao dictCommonDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Map<String, Object> getDict() {
        Map<String, Object> result = Maps.newLinkedHashMap();

        List<DictCommonDTO> dictCommonList = dictCommonDao.getDictList();

        Map<String, Map<String, String>> dictCommonMap = Maps.newLinkedHashMap();
        for (DictCommonDTO dictCommon : dictCommonList) {
                Map<String, String> codeMap = dictCommonMap.get(dictCommon.getTypeCode());
                if (codeMap == null || codeMap.isEmpty()) {
                    codeMap = Maps.newLinkedHashMap();
                    dictCommonMap.put(dictCommon.getTypeCode(), codeMap);
                }
                codeMap.put(dictCommon.getDictCode(), dictCommon.getDictDesc());
        }

        // 获取分类的字典信息
        List<CategoryDTO> categoryDTOS = categoryService.loadAllCategories();
        Map<String, String> codeMap = new HashMap<>();
        categoryDTOS.forEach(categoryDTO -> codeMap.put(categoryDTO.getCategoryId().toString(), categoryDTO.getCategory()));
        dictCommonMap.put("CategoryType", codeMap);

        result.putAll(dictCommonMap);
        return result;
    }

}
