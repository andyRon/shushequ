package top.andyron.shushequ.service.config.converter;
import org.springframework.util.CollectionUtils;
import top.andyron.shushequ.api.model.vo.article.dto.DictCommonDTO;
import top.andyron.shushequ.service.config.repository.entity.DictCommonDO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Banner转换
 *
 * @author andyron
 * @date 2026/4/17
 */
public class DictCommonConverter {

    public static List<DictCommonDTO> toDTOS(List<DictCommonDO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(DictCommonConverter::toDTO).collect(Collectors.toList());
    }

    public static DictCommonDTO toDTO(DictCommonDO dictCommonDO) {
        if (dictCommonDO == null) {
            return null;
        }
        DictCommonDTO dictCommonDTO = new DictCommonDTO();
        dictCommonDTO.setTypeCode(dictCommonDO.getTypeCode());
        dictCommonDTO.setDictCode(dictCommonDO.getDictCode());
        dictCommonDTO.setDictDesc(dictCommonDO.getDictDesc());
        dictCommonDTO.setSortNo(dictCommonDO.getSortNo());
        return dictCommonDTO;
    }
}
