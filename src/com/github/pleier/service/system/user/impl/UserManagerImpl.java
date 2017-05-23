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

    /**通过用户ID获取用户信息和角色信息
     * @param USER_ID
     * @return
     * @throws Exception
     */
    @Override
    public User getUserAndRoleById(String USER_ID) throws Exception {
        return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
    }

    /**通过USERNAEME获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findByUsername(PageData pd) throws Exception {
        return (PageData)dao.findForObject("UserMapper.findByUsername", pd);
    }

    /**列出某角色下的所有用户
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> listAllUserByRoldId(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserMapper.listAllUserByRoldId", pd);
    }

    /**保存用户IP
     * @param pd
     * @throws Exception
     */
    @Override
    public void saveIP(PageData pd) throws Exception {
        dao.update("UserMapper.saveIP", pd);
    }

    /**用户列表
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<PageData> listUsers(Page page) throws Exception {
        return (List<PageData>) dao.findForList("UserMapper.userlistPage", page);
    }

    /**通过邮箱获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findByUE(PageData pd) throws Exception {
        return (PageData)dao.findForObject("UserMapper.findByUE", pd);
    }

    /**通过编号获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findByUN(PageData pd) throws Exception {
        return (PageData)dao.findForObject("UserMapper.findByUN", pd);
    }

    /**通过id获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findById(PageData pd) throws Exception {
        return (PageData)dao.findForObject("UserMapper.findById", pd);
    }

    /**修改用户
     * @param pd
     * @throws Exception
     */
    @Override
    public void editU(PageData pd) throws Exception {
        dao.update("UserMapper.editU", pd);
    }

    /**保存用户
     * @param pd
     * @throws Exception
     */
    @Override
    public void saveU(PageData pd) throws Exception {
        dao.save("UserMapper.saveU", pd);
    }

    /**删除用户
     * @param pd
     * @throws Exception
     */
    @Override
    public void deleteU(PageData pd) throws Exception {
        dao.delete("UserMapper.deleteU", pd);
    }

    /**批量删除用户
     * @param USER_IDS
     * @throws Exception
     */
    @Override
    public void deleteAllU(String[] USER_IDS) throws Exception {
        dao.delete("UserMapper.deleteAllU", USER_IDS);
    }

    /**用户列表(全部)
     * @param pd
     * @throws Exception
     */
    @Override
    public List<PageData> listAllUser(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserMapper.listAllUser", pd);
    }

    /**获取总数
     * @param value
     * @throws Exception
     */
    @Override
    public PageData getUserCount(String value) throws Exception {
        return (PageData)dao.findForObject("UserMapper.getUserCount", value);
    }
}
