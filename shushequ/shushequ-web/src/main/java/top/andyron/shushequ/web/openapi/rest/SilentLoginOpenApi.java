package top.andyron.shushequ.web.openapi.rest;

import top.andyron.shushequ.api.model.openapi.user.OpenApiUserDTO;
import top.andyron.shushequ.api.model.vo.ResVo;
import top.andyron.shushequ.api.model.vo.constants.StatusEnum;
import top.andyron.shushequ.service.openapi.oauth.OpenApiSilentLoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andyron
 * @date 2026/4/19
 */
@RestController
@RequestMapping(path = "/openapi/login")
public class SilentLoginOpenApi {

    private final OpenApiSilentLoginService openApiSilentLoginService;

    public SilentLoginOpenApi(OpenApiSilentLoginService openApiSilentLoginService) {
        this.openApiSilentLoginService = openApiSilentLoginService;
    }

    @GetMapping(path = "loginByToken")
    public ResVo<OpenApiUserDTO> loginByToken(String token) {
        OpenApiUserDTO userInfo = openApiSilentLoginService.silentLogin(token);
        if (userInfo == null) {
            return ResVo.fail(StatusEnum.FORBID_NOTLOGIN);
        }

        return ResVo.ok(userInfo);
    }
}
