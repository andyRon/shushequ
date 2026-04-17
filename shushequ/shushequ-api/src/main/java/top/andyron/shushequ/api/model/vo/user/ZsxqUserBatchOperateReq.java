package top.andyron.shushequ.api.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class ZsxqUserBatchOperateReq implements Serializable {
    // ids
    private List<Long> ids;
    // 状态
    private Integer status;
}
