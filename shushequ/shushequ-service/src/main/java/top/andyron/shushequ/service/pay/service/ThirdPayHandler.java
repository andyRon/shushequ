
package top.andyron.shushequ.service.pay.service;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.andyron.shushequ.api.model.enums.pay.ThirdPayWayEnum;
import top.andyron.shushequ.core.util.SpringUtil;
import top.andyron.shushequ.service.pay.model.PayCallbackBo;
import top.andyron.shushequ.service.pay.model.PrePayInfoResBo;
import top.andyron.shushequ.service.pay.model.ThirdPayOrderReqBo;
import top.andyron.shushequ.service.pay.service.integration.ThirdPayIntegrationApi;
import top.andyron.shushequ.service.pay.service.integration.email.EmailPayIntegration;

import java.util.List;
import java.util.function.Function;

/**
 * 与三方支付服务交互的门面类
 *
 * @author andyron
 * @date 2026/4/17
 */
@Service
public class ThirdPayHandler {
    @Autowired
    private List<ThirdPayIntegrationApi> payServiceList;

    private ThirdPayIntegrationApi getPayService(ThirdPayWayEnum payWay) {
        return payServiceList.stream().filter(s -> s.support(payWay)).findFirst()
                .orElse(SpringUtil.getBean(EmailPayIntegration.class));
    }

    public PrePayInfoResBo createPayOrder(ThirdPayOrderReqBo payReq) {
        return getPayService(payReq.getPayWay()).createOrder(payReq);
    }

    public PayCallbackBo queryOrder(String outTradeNo, ThirdPayWayEnum payWay) {
        return getPayService(payWay).queryOrder(outTradeNo);
    }

    @Transactional
    public PayCallbackBo payCallback(HttpServletRequest request, ThirdPayWayEnum payWay) {
        return getPayService(payWay).payCallback(request);
    }

    @Transactional
    public <T> ResponseEntity<?> refundCallback(HttpServletRequest request, ThirdPayWayEnum payWay, Function<T, Boolean> refundCallback) {
        return getPayService(payWay).refundCallback(request, refundCallback);
    }
}
