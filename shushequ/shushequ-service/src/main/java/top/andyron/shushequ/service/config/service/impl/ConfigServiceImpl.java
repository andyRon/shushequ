package top.andyron.shushequ.service.config.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.enums.ConfigTypeEnum;
import top.andyron.shushequ.api.model.vo.banner.dto.ConfigDTO;
import top.andyron.shushequ.service.config.repository.dao.ConfigDao;
import top.andyron.shushequ.service.config.service.ConfigService;

import java.util.List;

/**
 * Banner前台接口
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public List<ConfigDTO> getConfigList(ConfigTypeEnum configTypeEnum) {
        return configDao.listConfigByType(configTypeEnum.getCode());
    }

    /**
     * 配置发生变更之后，失效本地缓存，这里主要是配合 SidebarServiceImpl 中的缓存使用
     *
     * @param configId
     * @param extra
     */
    @Override
    public void updateVisit(long configId, String extra) {
        configDao.updatePdfConfigVisitNum(configId, extra);
    }
}
