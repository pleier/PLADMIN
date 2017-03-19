package com.github.pleier.service.system.buttonrights.impl;

import com.github.pleier.dao.BaseDao;
import com.github.pleier.service.system.buttonrights.ButtonrightsManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pleiyang@outlook.com on 2017/3/7.
 */
@Service("buttonrightsService")
public class ButtonrightsManagerImpl implements ButtonrightsManager {
    @Resource(name="daoSupport")
    private BaseDao dao;

    /**新增
     * @param pd
     * @throws Exception
     */
    @Override
    public void save(PageData pd) throws Exception {
        dao.save("ButtonrightsMapper.save", pd);
    }

    /**
     *通过(角色ID和按钮ID)获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findById(PageData pd) throws Exception {
        return (PageData)dao.findForObject("ButtonrightsMapper.findById", pd);
    }

    /**
     * 删除
     * @param pd
     * @throws Exception
     */
    @Override
    public void delete(PageData pd) throws Exception {
        dao.delete("ButtonrightsMapper.delete", pd);
    }

    /**
     *列表(全部)
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> listAll(PageData pd) throws Exception {
        return (List<PageData>)dao.findForList("ButtonrightsMapper.listAll", pd);
    }

    /**
     * 列表(全部)左连接按钮表,查出安全权限标识
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> listAllBrAndQxname(PageData pd) throws Exception {
        return (List<PageData>)dao.findForList("ButtonrightsMapper.listAllBrAndQxname", pd);
    }
}
