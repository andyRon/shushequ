package top.andyron.shushequ.service.pay.model;
import lombok.Data;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.enums.pay.PayStatusEnum;

/**
 * 支付回调通知业务对象
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Accessors(chain = true)
public class PayCallbackBo {
    /**
     * 传递给支付系统的唯一外部单号
     */
    private String outTradeNo;
    /**
     * 对应系统内的业务支付id
     */
    private Long payId;
    /**
     * 支付成功时间
     */
    private Long successTime;

    /**
     * 三方流水编号
     */
    private String thirdTransactionId;

    /**
     * 支付状态
     */
    private PayStatusEnum payStatus;
}
