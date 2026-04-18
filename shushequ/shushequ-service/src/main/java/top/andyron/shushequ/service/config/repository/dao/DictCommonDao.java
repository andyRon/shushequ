package top.andyron.shushequ.service.config.repository.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import top.andyron.shushequ.api.model.vo.article.dto.DictCommonDTO;
import top.andyron.shushequ.service.config.converter.DictCommonConverter;
import top.andyron.shushequ.service.config.repository.entity.DictCommonDO;
import top.andyron.shushequ.service.config.repository.mapper.DictCommonMapper;

import java.util.List;

/**
 * @author andyron
 * @date 2026/4/17
 */
@Repository
public class DictCommonDao extends ServiceImpl<DictCommonMapper, DictCommonDO> {

    /**
     * 获取所有字典列表
     * @return
     */
    public List<DictCommonDTO> getDictList() {
        List<DictCommonDO> list = lambdaQuery().list();
        return DictCommonConverter.toDTOS(list);
    }
}
