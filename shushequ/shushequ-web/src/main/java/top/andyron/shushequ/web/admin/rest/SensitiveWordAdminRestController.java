package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.vo.PageVo;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.config.SearchSensitiveWordHitReq;
import top.andyron.shushequ.api.model.vo.config.SensitiveWordConfigReq;
import top.andyron.shushequ.api.model.vo.config.SensitiveWordOperateReq;
import top.andyron.shushequ.api.model.vo.config.dto.SensitiveWordConfigDTO;
import top.andyron.shushequ.api.model.vo.config.dto.SensitiveWordHitDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.andyron.shushequ.service.sensitive.service.SensitiveAdminService;

/**
 * 敏感词后台
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Permission(role = UserRole.LOGIN)
@Tag(name = "敏感词管理", description = "敏感词管理控制器")
@RequestMapping(path = {"/api/admin/sensitive/", "/admin/sensitive/"})
public class SensitiveWordAdminRestController {

    @Autowired
    private SensitiveAdminService sensitiveAdminService;

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "获取敏感词配置详情")
    @GetMapping(path = "detail")
    public ResVo<SensitiveWordConfigDTO> detail() {
        return ResVo.ok(sensitiveAdminService.getConfig());
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "分页获取敏感词命中统计")
    @PostMapping(path = "hit/list")
    public ResVo<PageVo<SensitiveWordHitDTO>> hitList(@RequestBody SearchSensitiveWordHitReq req) {
        return ResVo.ok(sensitiveAdminService.getHitWordPage(req));
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "保存敏感词配置")
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody SensitiveWordConfigReq req) {
        sensitiveAdminService.saveConfig(req);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @Operation(summary = "清除敏感词命中统计")
    @PostMapping(path = "hit/clear")
    public ResVo<String> clearHitWord(@RequestBody SensitiveWordOperateReq req) {
        sensitiveAdminService.clearHitWord(req.getWord());
        return ResVo.ok();
    }
}
