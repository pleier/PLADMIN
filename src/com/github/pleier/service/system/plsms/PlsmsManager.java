package com.github.pleier.service.system.plsms;

import com.github.pleier.entity.Page;
import com.github.pleier.util.PageData;

import java.util.List;

/**
 * 站内信接口
 * Created by pleiyang@outlook.com on 2017/5/16.
 */
public interface PlsmsManager {
    /**
     *新增
     * @param pageData
     * @throws Exception
     */
    void save(PageData pageData)throws Exception;

    /**
     * 删除
     * @param pageData
     * @throws Exception
     */
    void delete(PageData pageData)throws Exception;

    /**
     * 修改
     * @param pageData
     * @throws Exception
     */
    void edit(PageData pageData)throws Exception;

    /**
     *列表
     * @param page
     * @return
     * @throws Exception
     */
    List<PageData> list(Page page)throws Exception;

    /**
     *列表（全部）
     * @param pageData
     * @return
     * @throws Exception
     */
    List<PageData> listAll(PageData pageData)throws Exception;

    /**
     *通过id查询数据
     * @param pageData
     * @return
     * @throws Exception
     */
    PageData findById(PageData pageData)throws Exception;

    /**
     *获取未读总数
     * @param USERNAME
     * @return
     * @throws Exception
     */
    PageData findPlsmsCount(String USERNAME)throws Exception;

    /**
     *批量删除
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    void deleteAll(String[] ArrayDATA_IDS)throws Exception;
}
