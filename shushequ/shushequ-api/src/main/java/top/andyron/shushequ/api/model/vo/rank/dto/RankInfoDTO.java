package top.andyron.shushequ.api.model.vo.rank.dto;

import lombok.Data;
import top.andyron.shushequ.api.model.enums.rank.ActivityRankTimeEnum;

import java.util.List;

/**
 * 排行榜信息
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
public class RankInfoDTO {
    private ActivityRankTimeEnum time;
    private List<RankItemDTO> items;
}
