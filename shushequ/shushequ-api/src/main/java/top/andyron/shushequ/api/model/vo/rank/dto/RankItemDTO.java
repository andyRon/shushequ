package top.andyron.shushequ.api.model.vo.rank.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.vo.user.dto.SimpleUserInfoDTO;

/**
 * 排行榜信息
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Accessors(chain = true)
public class RankItemDTO {

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 用户
     */
    private SimpleUserInfoDTO user;
}
