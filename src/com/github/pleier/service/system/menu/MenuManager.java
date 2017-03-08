package com.github.pleier.service.system.menu;

import com.github.pleier.entity.system.Menu;
import com.github.pleier.util.PageData;

import java.util.List;

/**
 * 菜单处理接口
 * Created by pleiyang@outlook.com on 2017/3/5.
 */
public interface MenuManager {
    /**
     * @param parentId
     * @return
     * @throws Exception
     */
    public List<Menu> listSubMenuByParentId(String parentId)throws Exception;

    /**
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData getMenuById(PageData pd) throws Exception;

    /**
     * @param menu
     * @throws Exception
     */
    public void saveMenu(Menu menu) throws Exception;

    /**
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData findMaxId(PageData pd) throws Exception;

    /**
     * @param MENU_ID
     * @throws Exception
     */
    public void deleteMenuById(String MENU_ID) throws Exception;

    /**
     * @param menu
     * @throws Exception
     */
    public void edit(Menu menu) throws Exception;

    /**
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData editIcon(PageData pd) throws Exception;

    /**
     * @param MENU_ID
     * @return
     * @throws Exception
     */
    public List<Menu> listAllMenu(String MENU_ID) throws Exception;

    /**
     * @param MENU_ID
     * @return
     * @throws Exception
     */
    public List<Menu> listAllMenuQx(String MENU_ID) throws Exception;
}
