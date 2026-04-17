package top.andyron.shushequ.service.image.service;


import jakarta.servlet.http.HttpServletRequest;

/**
 * @author andyron
 * @date 2026/4/17
 */
public interface ImageService {
    /**
     * 图片转存
     * @param content
     * @return
     */
    String mdImgReplace(String content);


    /**
     * 外网图片转存
     *
     * @param img
     * @return
     */
    String saveImg(String img);

    /**
     * 保存图片
     *
     * @param request
     * @return
     */
    String saveImg(HttpServletRequest request);
}
