package com.github.pleier.controller.system.login;

import com.github.pleier.controller.system.base.BaseController;
import com.github.pleier.service.system.appuser.AppuserManager;
import com.github.pleier.service.system.buttonrights.ButtonrightsManager;
import com.github.pleier.service.system.menu.MenuManager;
import com.github.pleier.service.system.plbutton.PlbuttonManager;
import com.github.pleier.service.system.role.RoleManager;
import com.github.pleier.service.system.user.UserManager;
import com.github.pleier.util.Const;
import com.github.pleier.util.PageData;
import com.github.pleier.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 *
 * Created by PLEI on 2/10/2017.
 */

@Controller
public class LoginController extends BaseController{

    @Resource(name="userService")
    private UserManager userService;
    @Resource(name="menuService")
    private MenuManager menuService;
    @Resource(name="roleService")
    private RoleManager roleService;
    @Resource(name="buttonrightsService")
    private ButtonrightsManager buttonrightsService;
    @Resource(name="plbuttonService")
    private PlbuttonManager plbuttonManager;
    @Resource(name="appuserService")
    private AppuserManager appuserService;

    /**访问登录页
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_toLogin")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.setViewName("system/index/login");
        mv.addObject("pd",pd);
        return mv;
    }

}
