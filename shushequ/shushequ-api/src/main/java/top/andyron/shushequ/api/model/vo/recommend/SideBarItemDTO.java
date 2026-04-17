package top.andyron.shushequ.api.model.vo.recommend;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.cdn.CdnImgSerializer;
import top.andyron.shushequ.api.model.cdn.CdnUtil;

import java.util.List;

/**
 * 侧边推广信息
 *
 * @author andyron
 * @date 2026/4/17
 */
@Data
@Accessors(chain = true)
public class SideBarItemDTO {

    private String title;

    private String name;

    private String url;

    @JsonSerialize(using = CdnImgSerializer.class)
    private String img;

    private Long time;

    /**
     * tag列表
     */
    private List<Integer> tags;

    /**
     * 评分信息
     */
    private RateVisitDTO visit;

    public SideBarItemDTO setImg(String img) {
        this.img = CdnUtil.autoTransCdn(img);
        return this;
    }
}
