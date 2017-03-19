package com.github.pleier.service.system.plbutton.impl;

import com.github.pleier.dao.BaseDao;
import com.github.pleier.entity.Page;
import com.github.pleier.service.system.plbutton.PlbuttonManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pleiyang@outlook.com on 2017/3/9.
 */
@Service("plbuttonService")
public class PlbuttonManagerImpl implements PlbuttonManager {
    @Resource(name="daoSupport")
    private BaseDao dao;

    /**新增
     * @param pd
     * @throws Exception
     */
    @Override
    public void save(PageData pd) throws Exception {
        dao.save("PlbuttonMapper.save", pd);
    }

    /**删除
     * @param pd
     * @throws Exception
     */
    @Override
    public void delete(PageData pd) throws Exception {
        dao.delete("PlbuttonMapper.delete", pd);
    }

    /**修改
     * @param pd
     * @throws Exception
     */
    @Override
    public void edit(PageData pd) throws Exception {
        dao.update("PlbuttonMapper.edit", pd);
    }

    /**列表
     * @param page
     * @throws Exception
     */
    @Override
    public List<PageData> list(Page page) throws Exception {
        return (List<PageData>)dao.findForList("PlbuttonMapper.datalistPage", page);
    }

    /**列表(全部)
     * @param pd
     * @throws Exception
     */
    @Override
    public List<PageData> listAll(PageData pd) throws Exception {
        return (List<PageData>)dao.findForList("PlbuttonMapper.listAll", pd);
    }

    /**通过id获取数据
     * @param pd
     * @throws Exception
     */
    @Override
    public PageData findById(PageData pd) throws Exception {
        return (PageData)dao.findForObject("PlbuttonMapper.findById", pd);
    }

    /**批量删除
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    @Override
    public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
        dao.delete("PlbuttonMapper.deleteAll", ArrayDATA_IDS);
    }
}
