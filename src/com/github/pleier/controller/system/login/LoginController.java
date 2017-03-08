package com.github.pleier.controller.system.login;

import com.github.pleier.controller.system.base.BaseController;
import com.github.pleier.service.system.buttonrights.ButtonrightsManager;
import com.github.pleier.service.system.menu.MenuManager;
import com.github.pleier.service.system.role.RoleManager;
import com.github.pleier.service.system.user.UserManager;

import javax.annotation.Resource;

/**
 *
 * Created by PLEI on 2/10/2017.
 */
public class LoginController extends BaseController{

    @Resource(name="userService")
    private UserManager userService;
    @Resource(name="menuService")
    private MenuManager menuService;
    @Resource(name="roleService")
    private RoleManager roleService;
    @Resource(name="buttonrightsService")
    private ButtonrightsManager buttonrightsService;

}
