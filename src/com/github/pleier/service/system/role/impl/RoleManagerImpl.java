package com.github.pleier.service.system.role.impl;

import com.github.pleier.dao.BaseDao;
import com.github.pleier.entity.system.Role;
import com.github.pleier.service.system.role.RoleManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by pleiyang@outlook.com on 2017/3/7.
 */
@Repository("roleService")
public class RoleManagerImpl implements RoleManager {
    @Resource(name="daoSupport")
    private BaseDao dao;

    /**根据父级id列出此组下级角色
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> listAllRolesByPId(PageData pd) throws Exception {
        return (List<Role>)dao.findForList("RoleMapper.listAllRolesByPId", pd);
    }

    /**通过id查找角色
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public PageData findObjectById(PageData pd) throws Exception {
        return (PageData)dao.findForObject("RoleMapper.findObjectById", pd);
    }

    /**
     * 添加角色
     * @param pd
     * @throws Exception
     */
    @Override
    public void add(PageData pd) throws Exception {
        dao.save("RoleMapper.insert", pd);
    }

    /**
     * 修改角色
     * @param pd
     * @throws Exception
     */
    @Override
    public void edit(PageData pd) throws Exception {
        dao.update("RoleMapper.edit", pd);
    }

    /**
     * 通过id删除角色
     * @param ROLE_ID
     * @throws Exception
     */
    @Override
    public void deleteRoleById(String ROLE_ID) throws Exception {
        dao.delete("RoleMapper.deleteRoleById", ROLE_ID);
    }

    /**给当前角色附加菜单权限
     * @param role
     * @throws Exception
     */
    @Override
    public void updateRoleRights(Role role) throws Exception {
        dao.update("RoleMapper.updateRoleRights", role);
    }

    /**
     *
     * @param ROLE_ID
     * @return
     * @throws Exception
     */
    @Override
    public Role getRoleById(String ROLE_ID) throws Exception {
       return (Role) dao.findForObject("RoleMapper.getRoleById", ROLE_ID);
    }

    /**给全部子角色加菜单权限
     * @param pd
     * @throws Exception
     */
    @Override
    public void setAllRights(PageData pd) throws Exception {
        dao.update("RoleMapper.setAllRights", pd);
    }

    /**权限(增删改查)
     * @param msg 区分增删改查
     * @param pd
     * @throws Exception
     */
    @Override
    public void saveB4Button(String msg, PageData pd) throws Exception {
        dao.update("RoleMapper."+msg, pd);
    }
}
