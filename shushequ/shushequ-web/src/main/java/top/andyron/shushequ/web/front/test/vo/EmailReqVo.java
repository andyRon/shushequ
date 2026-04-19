package top.andyron.shushequ.web.front.test.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 邮件发送验证
 *
 * @author andyron
 * @date 2026/4/19
 */
@Data
public class EmailReqVo implements Serializable {
    private static final long serialVersionUID = -8560585303684975482L;

    private String to;

    private String title;

    private String content;

}
