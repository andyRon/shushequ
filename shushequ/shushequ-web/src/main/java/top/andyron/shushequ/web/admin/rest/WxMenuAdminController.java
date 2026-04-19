package top.andyron.shushequ.web.admin.rest;

import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.wx.menu.*;
import top.andyron.shushequ.core.permission.Permission;
import top.andyron.shushequ.core.permission.UserRole;
import top.andyron.shushequ.service.config.service.WxMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信菜单后台控制器
 *
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@Permission(role = UserRole.LOGIN)
@Tag(name = "微信菜单", description = "微信菜单后台控制器")
@RequestMapping(path = {"api/admin/wx/menu/", "admin/wx/menu/"})
public class WxMenuAdminController {
    @Resource
    private WxMenuService wxMenuService;

    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "detail")
    public ResVo<WxMenuDetailDTO> detail() {
        return ResVo.ok(wxMenuService.getDetail());
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody WxMenuSaveReq req) {
        wxMenuService.saveDraft(req);
        return ResVo.ok();
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "validate")
    public ResVo<WxMenuValidateResDTO> validate(@RequestBody(required = false) WxMenuValidateReq req) {
        return ResVo.ok(wxMenuService.validate(req));
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "publish")
    public ResVo<WxMenuPublishResDTO> publish(@RequestBody(required = false) WxMenuPublishReq req) {
        return ResVo.ok(wxMenuService.publish(req));
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "sync")
    public ResVo<WxMenuDetailDTO> sync() {
        return ResVo.ok(wxMenuService.syncRemoteToDraft());
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "preview/match")
    public ResVo<WxMenuPreviewMatchResDTO> previewMatch(@RequestBody(required = false) WxMenuPreviewMatchReq req) {
        return ResVo.ok(wxMenuService.previewMatch(req));
    }

    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "preview/ai")
    public ResVo<WxMenuPreviewAiResDTO> previewAi(@RequestBody(required = false) WxMenuPreviewAiReq req) {
        return ResVo.ok(wxMenuService.previewAi(req));
    }
}
