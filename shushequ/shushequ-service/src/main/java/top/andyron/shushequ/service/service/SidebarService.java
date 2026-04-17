package top.andyron.shushequ.service.service;


import top.andyron.shushequ.api.model.vo.recommend.SideBarDTO;

import java.util.List;

/**
 * @author andyron
 * @date 2026/4/17
 */
public interface SidebarService {

    /**
     * 查询首页的侧边栏信息
     *
     * @return
     */
    List<SideBarDTO> queryHomeSidebarList();

    /**
     * 查询教程的侧边栏信息
     *
     * @return
     */
    List<SideBarDTO> queryColumnSidebarList();

    /**
     * 查询文章详情的侧边栏信息
     *
     * @param author    文章作者id
     * @param articleId 文章id
     * @return
     */
    List<SideBarDTO> queryArticleDetailSidebarList(Long author, Long articleId);

}
