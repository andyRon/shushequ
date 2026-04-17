package top.andyron.shushequ.service.pay.service.integration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import top.andyron.shushequ.api.model.enums.pay.ThirdPayWayEnum;
import top.andyron.shushequ.service.pay.model.PayCallbackBo;
import top.andyron.shushequ.service.pay.model.PrePayInfoResBo;
import top.andyron.shushequ.service.pay.model.ThirdPayOrderReqBo;

import java.io.IOException;
import java.util.function.Function;

/**
 * 对接三方支付的API定义
 *
 * @author andyron
 * @date 2026/4/17
 */
public interface ThirdPayIntegrationApi {

    boolean support(ThirdPayWayEnum payWay);

    /**
     * 下单
     *
     * @param payReq
     * @return
     */
    PrePayInfoResBo createOrder(ThirdPayOrderReqBo payReq);


    /**
     * 查询订单
     *
     * @param outTradeNo
     * @return
     */
    PayCallbackBo queryOrder(String outTradeNo);


    /**
     * 支付回调
     *
     * @param request
     * @return
     */
    PayCallbackBo payCallback(HttpServletRequest request);

    /**
     * 关单
     *
     * @param outTradeNo
     */
    void closeOrder(String outTradeNo);

    /**
     * 退款回调
     *
     * @param request        携带回传的请求参数
     * @param refundCallback 退款结果回调执行业务逻辑
     * @return
     * @throws IOException
     */
    default <T> ResponseEntity<?> refundCallback(HttpServletRequest request, Function<T, Boolean> refundCallback) {
        return ResponseEntity.ok(true);
    }
}
