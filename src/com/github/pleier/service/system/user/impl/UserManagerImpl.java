package com.github.pleier.service.system.user.impl;

import com.github.pleier.entity.Page;
import com.github.pleier.entity.system.User;
import com.github.pleier.service.system.user.UserManager;
import com.github.pleier.util.PageData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pleiyang@outlook.com on 2017/3/4.
 */

@Service("userService")
public class UserManagerImpl implements UserManager {

    @Override
    public PageData getUserByNameAndPwd(PageData pd) throws Exception {
        return null;
    }

    @Override
    public void updateLastLogin(PageData pd) throws Exception {

    }

    @Override
    public User getUserAndRoleById(String USER_ID) throws Exception {
        return null;
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
