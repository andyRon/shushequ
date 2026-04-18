package top.andyron.shushequ.service.config.converter;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.andyron.shushequ.api.model.vo.banner.ConfigReq;
import top.andyron.shushequ.api.model.vo.banner.SearchConfigReq;
import top.andyron.shushequ.api.model.vo.banner.dto.ConfigDTO;
import top.andyron.shushequ.api.model.vo.config.GlobalConfigReq;
import top.andyron.shushequ.api.model.vo.config.SearchGlobalConfigReq;
import top.andyron.shushequ.api.model.vo.config.dto.GlobalConfigDTO;
import top.andyron.shushequ.service.config.repository.entity.ConfigDO;
import top.andyron.shushequ.service.config.repository.entity.GlobalConfigDO;
import top.andyron.shushequ.service.config.repository.params.SearchConfigParams;
import top.andyron.shushequ.service.config.repository.params.SearchGlobalConfigParams;

import java.util.List;

@Mapper
public interface ConfigStructMapper {
    // instance
    ConfigStructMapper INSTANCE = Mappers.getMapper( ConfigStructMapper.class );

    // req to params
    @Mapping(source = "pageNumber", target = "pageNum")
    SearchConfigParams toSearchParams(SearchConfigReq req);

    // req to params
    @Mapping(source = "pageNumber", target = "pageNum")
    // key to keywords
    @Mapping(source = "keywords", target = "key")
    SearchGlobalConfigParams toSearchGlobalParams(SearchGlobalConfigReq req);

    // do to dto
    ConfigDTO toDTO(ConfigDO configDO);

    List<ConfigDTO> toDTOS(List<ConfigDO> configDOS);

    ConfigDO toDO(ConfigReq configReq);

    // do to dto
    // key to keywords
    @Mapping(source = "key", target = "keywords")
    GlobalConfigDTO toGlobalDTO(GlobalConfigDO configDO);

    List<GlobalConfigDTO> toGlobalDTOS(List<GlobalConfigDO> configDOS);

    @Mapping(source = "keywords", target = "key")
    GlobalConfigDO toGlobalDO(GlobalConfigReq req);
}
