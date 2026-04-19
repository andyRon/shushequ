package top.andyron.shushequ.api.model.vo.article.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import top.andyron.shushequ.api.model.util.cdn.CdnImgSerializer;
import top.andyron.shushequ.api.model.util.cdn.CdnUtil;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SimpleColumnDTO implements Serializable {

    private static final long serialVersionUID = 3646376715620165839L;

    @Schema(description = "专栏id")
    private Long columnId;

    @Schema(description = "专栏名")
    private String column;

    // 封面
    @Schema(description = "封面")
    @JsonSerialize(using = CdnImgSerializer.class)
    private String cover;

    public SimpleColumnDTO setCover(String cover) {
        this.cover = CdnUtil.autoTransCdn( cover);
        return this;
    }
}
