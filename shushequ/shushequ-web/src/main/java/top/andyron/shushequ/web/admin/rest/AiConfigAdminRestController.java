package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.ai.config.AiConfigAdminReq;
import top.andyron.shushequ.api.model.vo.ai.config.AiConfigTestReq;
import top.andyron.shushequ.api.model.vo.ai.config.dto.AiConfigAdminDTO;
import top.andyron.shushequ.api.model.vo.ai.config.dto.AiConfigTestDTO;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.service.chatai.config.AiConfigAdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AI 配置管理控制器
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Permission(role = UserRole.LOGIN)
@Tag(name = "AI 配置", description = "AI 配置管理控制器")
@RequestMapping(path = {"api/admin/ai/config/", "admin/ai/config/"})
public class AiConfigAdminRestController {
    @Resource
    private AiConfigAdminService aiConfigAdminService;

    @GetMapping(path = "detail")
    @Permission(role = UserRole.ADMIN)
    public ResVo<AiConfigAdminDTO> detail() {
        return ResVo.ok(aiConfigAdminService.getConfig());
    }

    @PostMapping(path = "save")
    @Permission(role = UserRole.ADMIN)
    public ResVo<String> save(@RequestBody AiConfigAdminReq req) {
        aiConfigAdminService.save(req);
        return ResVo.ok();
    }

    @PostMapping(path = "test")
    @Permission(role = UserRole.ADMIN)
    public ResVo<AiConfigTestDTO> test(@RequestBody AiConfigTestReq req) {
        return ResVo.ok(aiConfigAdminService.test(req));
    }
}
