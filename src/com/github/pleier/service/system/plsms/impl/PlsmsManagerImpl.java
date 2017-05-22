package com.github.pleier.service.system.plsms.impl;

import com.github.pleier.dao.BaseDao;
import com.github.pleier.entity.Page;
import com.github.pleier.service.system.plsms.PlsmsManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pleiyang@outlook.com on 2017/5/16.
 */
@Service("plsmsService")
public class PlsmsManagerImpl implements PlsmsManager {
    @Resource(name="daoSupport")
    private BaseDao dao;
    /**
     *新增
     * @param pageData
     * @throws Exception
     */
    @Override
    public void save(PageData pageData) throws Exception {
        dao.save("PlsmsMapper.save", pageData);
    }

    /**
     * 删除
     * @param pageData
     * @throws Exception
     */
    @Override
    public void delete(PageData pageData) throws Exception {
        dao.delete("PlsmsMapper.delete", pageData);
    }

    /**
     * 修改状态
     * @param pageData
     * @throws Exception
     */
    @Override
    public void edit(PageData pageData) throws Exception {
        dao.update("PlsmsMapper.edit", pageData);
    }

    /**
     *列表
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> list(Page page) throws Exception {
        return (List<PageData>)dao.findForList("PlsmsMapper.datalistPage", page);
    }

    /**
     *列表（全部）
     * @param pageData
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> listAll(PageData pageData) throws Exception {
        return (List<PageData>)dao.findForList("PlsmsMapper.listAll", pageData);
    }

    /**
     *通过id查询数据
     * @param pageData
     * @return
     * @throws Exception
     */
    @Override
    public PageData findById(PageData pageData) throws Exception {
        return (PageData)dao.findForObject("PlsmsMapper.findById", pageData);
    }

    /**
     *获取未读总数
     * @param USERNAME
     * @return
     * @throws Exception
     */
    @Override
    public PageData findPlsmsCount(String USERNAME) throws Exception {
        return (PageData)dao.findForObject("PlsmsMapper.findFhsmsCount", USERNAME);
    }

    /**
     *批量删除
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    @Override
    public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
        dao.delete("PlsmsMapper.deleteAll", ArrayDATA_IDS);
    }
}
