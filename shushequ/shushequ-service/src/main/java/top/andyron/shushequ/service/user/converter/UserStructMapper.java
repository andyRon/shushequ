package top.andyron.shushequ.service.user.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.andyron.shushequ.api.model.vo.user.SearchZsxqUserReq;
import top.andyron.shushequ.service.user.repository.params.SearchZsxqWhiteParams;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Mapper
public interface UserStructMapper {
    UserStructMapper INSTANCE = Mappers.getMapper( UserStructMapper.class );
    // req to params
    @Mapping(source = "pageNumber", target = "pageNum")
    // state to status
    @Mapping(source = "state", target = "status")
    SearchZsxqWhiteParams toSearchParams(SearchZsxqUserReq req);
}
