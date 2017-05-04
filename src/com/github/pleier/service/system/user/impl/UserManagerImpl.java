package com.github.pleier.service.system.user.impl;

import com.github.pleier.dao.BaseDao;
import com.github.pleier.entity.Page;
import com.github.pleier.entity.system.User;
import com.github.pleier.service.system.user.UserManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户
 * Created by pleiyang@outlook.com on 2017/3/4.
 */

@Service("userService")
public class UserManagerImpl implements UserManager {
    @Resource(name="daoSupport")
    private BaseDao dao;

    /**
     * 登录判断
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData getUserByNameAndPwd(PageData pd) throws Exception {
        return (PageData)dao.findForObject("UserMapper.getUserInfo", pd);
    }

    /**
     * 更新登录时间
     * @param pd
     * @throws Exception
     */
    @Override
    public void updateLastLogin(PageData pd) throws Exception {
        dao.update("UserMapper.updateLastLogin", pd);
    }

    @Override
    public User getUserAndRoleById(String USER_ID) throws Exception {
        return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
    }

    @Override
    public PageData findByUsername(PageData pd) throws Exception {
        return null;
    }

    @Override
    public List<PageData> listAllUserByRoldId(PageData pd) throws Exception {
        return null;
    }

    @Override
    public void saveIP(PageData pd) throws Exception {

    }

    @Override
    public List<PageData> listUsers(Page page) throws Exception {
        return null;
    }

    @Override
    public PageData findByUE(PageData pd) throws Exception {
        return null;
    }

    @Override
    public PageData findByUN(PageData pd) throws Exception {
        return null;
    }

    @Override
    public PageData findById(PageData pd) throws Exception {
        return null;
    }

    @Override
    public void editU(PageData pd) throws Exception {

    }

    @Override
    public void saveU(PageData pd) throws Exception {

    }

    @Override
    public void deleteU(PageData pd) throws Exception {

    }

    @Override
    public void deleteAllU(String[] USER_IDS) throws Exception {

    }

    @Override
    public List<PageData> listAllUser(PageData pd) throws Exception {
        return null;
    }

    @Override
    public PageData getUserCount(String pd) throws Exception {
        return null;
    }
}
