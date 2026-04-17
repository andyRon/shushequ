package top.andyron.shushequ.service.article.conveter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.andyron.shushequ.api.model.vo.article.SearchColumnArticleReq;
import top.andyron.shushequ.service.article.repository.params.SearchColumnArticleParams;

@Mapper
public interface ColumnArticleStructMapper {
    ColumnArticleStructMapper INSTANCE = Mappers.getMapper( ColumnArticleStructMapper.class );

    SearchColumnArticleParams toSearchParams(SearchColumnArticleReq req);

    ColumnArticleParams toParams(ColumnArticleReq req);

    ColumnArticleDO reqToDO(ColumnArticleReq req);
}
