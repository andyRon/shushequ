package top.andyron.shushequ.service.article.conveter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.andyron.shushequ.api.model.vo.article.SearchTagReq;
import top.andyron.shushequ.api.model.vo.article.TagReq;
import top.andyron.shushequ.api.model.vo.article.dto.TagDTO;
import top.andyron.shushequ.service.article.repository.entity.TagDO;
import top.andyron.shushequ.service.article.repository.params.SearchTagParams;

import java.util.List;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Mapper
public interface TagStructMapper {
    // instance
    TagStructMapper INSTANCE = Mappers.getMapper( TagStructMapper.class );

    // req to params
    @Mapping(source = "pageNumber", target = "pageNum")
    SearchTagParams toSearchParams(SearchTagReq req);

    // do to dto
    @Mapping(source = "id", target = "tagId")
    @Mapping(source = "tagName", target = "tag")
    TagDTO toDTO(TagDO tagDO);

    List<TagDTO> toDTOs(List<TagDO> list);

    @Mapping(source = "tag", target = "tagName")
    TagDO toDO(TagReq tagReq);
}
