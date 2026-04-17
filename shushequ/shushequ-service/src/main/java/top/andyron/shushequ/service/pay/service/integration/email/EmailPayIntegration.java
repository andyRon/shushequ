package top.andyron.shushequ.service.pay.service.integration.email;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.andyron.shushequ.api.model.enums.pay.PayStatusEnum;
import top.andyron.shushequ.api.model.enums.pay.ThirdPayWayEnum;
import top.andyron.shushequ.api.model.vo.user.dto.BaseUserInfoDTO;
import top.andyron.shushequ.service.pay.model.PayCallbackBo;
import top.andyron.shushequ.service.pay.model.PrePayInfoResBo;
import top.andyron.shushequ.service.pay.model.ThirdPayOrderReqBo;
import top.andyron.shushequ.service.pay.service.integration.ThirdPayIntegrationApi;
import top.andyron.shushequ.service.user.service.UserService;

import java.util.Objects;

/**
 * 个人收款码，基于微信的支付方式
 *
 * @author andyron
 * @date 2026/4/17
 */
@Slf4j
@Service
public class EmailPayIntegration implements ThirdPayIntegrationApi {
    @Autowired
    private UserService userService;

    @Override
    public boolean support(ThirdPayWayEnum payWay) {
        return payWay == ThirdPayWayEnum.EMAIL;
    }

    @Override
    public PrePayInfoResBo createOrder(ThirdPayOrderReqBo payReq) {
        PrePayInfoResBo resBo = new PrePayInfoResBo();
        resBo.setPayWay(ThirdPayWayEnum.EMAIL);
        resBo.setOutTradeNo(payReq.getOutTradeNo());

        BaseUserInfoDTO receiveUserInfo = userService.queryBasicUserInfo(Long.parseLong(payReq.getOpenId()));
        resBo.setPrePayId(receiveUserInfo.getPayCode());
        resBo.setExpireTime(System.currentTimeMillis() + ThirdPayWayEnum.EMAIL.getExpireTimePeriod());
        return resBo;
    }

    @Override
    public void closeOrder(String outTradeNo) {
    }

    @Override
    public PayCallbackBo queryOrder(String outTradeNo) {
        return new PayCallbackBo().setOutTradeNo(outTradeNo);
    }


    @Override
    public PayCallbackBo payCallback(HttpServletRequest request) {
        String outTradeNo = request.getParameter("verifyCode");
        Long payId = Long.parseLong(request.getParameter("payId"));
        PayStatusEnum payStatus = Objects.equals("true", request.getParameter("succeed")) ? PayStatusEnum.SUCCEED : PayStatusEnum.FAIL;
        return new PayCallbackBo().setPayId(payId).setOutTradeNo(outTradeNo).setPayStatus(payStatus)
                .setSuccessTime(System.currentTimeMillis());
    }
}
