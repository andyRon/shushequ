package top.andyron.shushequ.service.article.conveter;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.andyron.shushequ.api.model.vo.article.CategoryReq;
import top.andyron.shushequ.api.model.vo.article.dto.CategoryDTO;
import top.andyron.shushequ.service.article.repository.entity.CategoryDO;

import java.util.List;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Mapper
public interface CategoryStructMapper {
    // instance
    CategoryStructMapper INSTANCE = Mappers.getMapper( CategoryStructMapper.class );

    // req to params
    @Mapping(source = "pageNumber", target = "pageNum")
    SearchCategoryParams toSearchParams(SearchCategoryReq req);

    // do to dto
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "categoryName", target = "category")
    CategoryDTO toDTO(CategoryDO categoryDO);

    List<CategoryDTO> toDTOs(List<CategoryDO> list);

    // req to do
    @Mapping(source = "category", target = "categoryName")
    CategoryDO toDO(CategoryReq categoryReq);
}
