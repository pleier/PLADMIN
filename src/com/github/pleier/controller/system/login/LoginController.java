package com.github.pleier.controller.system.login;

import com.github.pleier.controller.system.base.BaseController;
import com.github.pleier.entity.system.Menu;
import com.github.pleier.entity.system.Role;
import com.github.pleier.entity.system.User;
import com.github.pleier.service.system.appuser.AppuserManager;
import com.github.pleier.service.system.buttonrights.ButtonrightsManager;
import com.github.pleier.service.system.menu.MenuManager;
import com.github.pleier.service.system.plbutton.PlbuttonManager;
import com.github.pleier.service.system.role.RoleManager;
import com.github.pleier.service.system.user.UserManager;
import com.github.pleier.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PlbuttonManager plbuttonService;
    @Resource(name="appuserService")
    private AppuserManager appuserService;

    /**访问登录页
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_toLogin")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd ;
        pd = this.getPageData();
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.setViewName("system/index/login");
        mv.addObject("pd",pd);
        return mv;
    }

    /**
     * 验证登录信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_login",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object login() throws Exception{
        PageData pageData = this.getPageData();
        Map<String,String> map = new HashMap<String,String>();
        String errorInfo="";
        String[] keyData = pageData.getString("KEYDATA").replaceAll("qq247151115pl","").split(",pl,");
        if(null!=keyData&&keyData.length==2){
            Session session = Jurisdiction.getSession();
            String userName = keyData[0];//用户名
            String password = keyData[1];//密码
            pageData.put("USERNAME",userName);
            String passwd = new SimpleHash("SHA-1",userName,password).toString();//密码加密
            pageData.put("PASSWORD",passwd);
            pageData=userService.getUserByNameAndPwd(pageData);//根据用户名和密码去查询用户信息
            if(null!=pageData){
                pageData.put("LAST_LOGIN", DateUtil.getTime().toString());
                userService.updateLastLogin(pageData);
                User user = new User();
                user.setUSER_ID(pageData.getString("USER_ID"));
                user.setUSERNAME(pageData.getString("USERNAME"));
                user.setPASSWORD(pageData.getString("PASSWORD"));
                user.setNAME(pageData.getString("NAME"));
                user.setRIGHTS(pageData.getString("RIGHTS"));
                user.setROLE_ID(pageData.getString("ROLE_ID"));
                user.setLAST_LOGIN(pageData.getString("LAST_LOGIN"));
                user.setIP(pageData.getString("IP"));
                user.setSTATUS(pageData.getString("STATUS"));
                session.setAttribute(Const.SESSION_USER, user);
                //shiro加入身份验证
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
                try {
                    subject.login(token);
                } catch (AuthenticationException e) {
                    errorInfo = "身份验证失败！";
                }
            }else{
                errorInfo = "usererror"; 				//用户名或密码有误
                logBefore(logger, userName+"登录系统密码或用户名错误");
            }
            if(Tools.isEmpty(errorInfo)){
                errorInfo = "success";					//验证成功
                logBefore(logger, userName+"登录系统");
            }
        }
        map.put("result", errorInfo);
        return AppUtil.returnObject(new PageData(), map);
    }

    /**访问系统首页
     * @param changeMenu：切换菜单参数
     * @return
     */
    @RequestMapping(value="/main/{changeMenu}")
    public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
        ModelAndView mv = this.getModelAndView();
        PageData pd ;
        pd = this.getPageData();
        try{
            Session session = Jurisdiction.getSession();                            //shiro管理的session
            User user = (User)session.getAttribute(Const.SESSION_USER);				//读取session中的用户信息(单独用户信息)
            if (user != null) {
                User userr = (User)session.getAttribute(Const.SESSION_USERROL);		//读取session中的用户信息(含角色信息)
                if(null == userr){
                    user = userService.getUserAndRoleById(user.getUSER_ID());		//通过用户ID读取用户信息和角色信息
                    session.setAttribute(Const.SESSION_USERROL, user);				//存入session
                }else{
                    user = userr;
                }
                String USERNAME = user.getUSERNAME();                               //获取用户名
                Role role = user.getRole();											//获取用户角色
                String roleRights = role!=null ? role.getRIGHTS() : "";				//角色权限(菜单权限)
                session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
                session.setAttribute(Const.SESSION_USERNAME, USERNAME);				//放入用户名到session
                List<Menu> allmenuList = new ArrayList<Menu>();
                if(null == session.getAttribute(USERNAME + Const.SESSION_ALLMENULIST)){
                    allmenuList = menuService.listAllMenuQx("0");					//获取所有菜单
                    if(Tools.notEmpty(roleRights)){
                        allmenuList = this.readMenu(allmenuList, roleRights);		//根据角色权限获取本权限的菜单列表
                    }
                    session.setAttribute(USERNAME + Const.SESSION_ALLMENULIST, allmenuList);//菜单权限放入session中
                }else{
                    allmenuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_ALLMENULIST);
                }
                //切换菜单处理=====start
                List<Menu> menuList = new ArrayList<Menu>();
                if(null == session.getAttribute(USERNAME + Const.SESSION_MENULIST) || ("yes".equals(changeMenu))){
                    List<Menu> menuList1 = new ArrayList<Menu>();
                    List<Menu> menuList2 = new ArrayList<Menu>();
                    //拆分菜单
                    for(int i=0;i<allmenuList.size();i++){
                        Menu menu = allmenuList.get(i);
                        if("1".equals(menu.getMENU_TYPE())){
                            menuList1.add(menu);
                        }else{
                            menuList2.add(menu);
                        }
                    }
                    session.removeAttribute(USERNAME + Const.SESSION_MENULIST);
                    if("2".equals(session.getAttribute("changeMenu"))){
                        session.setAttribute(USERNAME + Const.SESSION_MENULIST, menuList1);
                        session.removeAttribute("changeMenu");
                        session.setAttribute("changeMenu", "1");
                        menuList = menuList1;
                    }else{
                        session.setAttribute(USERNAME + Const.SESSION_MENULIST, menuList2);
                        session.removeAttribute("changeMenu");
                        session.setAttribute("changeMenu", "2");
                        menuList = menuList2;
                    }
                }else{
                    menuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_MENULIST);
                }
                //切换菜单处理=====end
                if(null == session.getAttribute(USERNAME + Const.SESSION_QX)){
                    session.setAttribute(USERNAME + Const.SESSION_QX, this.getUQX(USERNAME));	//按钮权限放到session中
                }
                this.getRemortIP(USERNAME);	//更新登录IP
                mv.setViewName("system/index/main");
                mv.addObject("user", user);
                mv.addObject("menuList", menuList);
            }else {
                mv.setViewName("system/index/login");//session失效后跳转登录页面
            }
        } catch(Exception e){
            mv.setViewName("system/index/login");
            logger.error(e.getMessage(), e);
        }
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.addObject("pd",pd);
        return mv;
    }


    /**根据角色权限获取本权限的菜单列表(递归处理)
     * @param menuList：传入的总菜单
     * @param roleRights：加密的权限字符串
     * @return
     */
    public List<Menu> readMenu(List<Menu> menuList,String roleRights){
        for(int i=0;i<menuList.size();i++){
            menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
            if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
                this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
            }
        }
        return menuList;
    }

    /** 更新登录用户的IP
     * @param USERNAME
     * @throws Exception
     */
    public void getRemortIP(String USERNAME) throws Exception {
        PageData pd = new PageData();
        HttpServletRequest request = this.getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        }else{
            ip = request.getHeader("x-forwarded-for");
        }
        pd.put("USERNAME", USERNAME);
        pd.put("IP", ip);
        userService.saveIP(pd);
    }

    /**获取用户权限
     * @param USERNAME
     * @return
     */
    public Map<String, String> getUQX(String USERNAME){
        PageData pd = new PageData();
        Map<String, String> map = new HashMap<String, String>();
        try {
            pd.put(Const.SESSION_USERNAME, USERNAME);
            pd.put("ROLE_ID", userService.findByUsername(pd).get("ROLE_ID").toString());//获取角色ID
            pd = roleService.findObjectById(pd);										//获取角色信息
            map.put("adds", pd.getString("ADD_QX"));	//增
            map.put("dels", pd.getString("DEL_QX"));	//删
            map.put("edits", pd.getString("EDIT_QX"));	//改
            map.put("chas", pd.getString("CHA_QX"));    //查
            List<PageData> buttonQXnamelist = new ArrayList<PageData>();
            if("admin".equals(USERNAME)){
                buttonQXnamelist = plbuttonService.listAll(pd);					//admin用户拥有所有按钮权限
            }else{
                buttonQXnamelist = buttonrightsService.listAllBrAndQxname(pd);	//此角色拥有的按钮权限标识列表
            }
            for(int i=0;i<buttonQXnamelist.size();i++){
                map.put(buttonQXnamelist.get(i).getString("QX_NAME"),"1");		//按钮权限
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return map;
    }

    /**
     * 用户注销
     * @param
     * @return
     */
    @RequestMapping(value="/logout")
    public ModelAndView logout(){
        String USERNAME = Jurisdiction.getUsername();	//当前登录的用户名
        logBefore(logger, USERNAME+"退出系统");
        ModelAndView mv = this.getModelAndView();
        PageData pd ;
        Session session = Jurisdiction.getSession();	//以下清除session缓存
        session.removeAttribute(Const.SESSION_USER);
        session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
        session.removeAttribute(USERNAME + Const.SESSION_ALLMENULIST);
        session.removeAttribute(USERNAME + Const.SESSION_MENULIST);
        session.removeAttribute(USERNAME + Const.SESSION_QX);
        session.removeAttribute(Const.SESSION_userpds);
        session.removeAttribute(Const.SESSION_USERNAME);
        session.removeAttribute(Const.SESSION_USERROL);
        session.removeAttribute("changeMenu");
        //shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        pd = this.getPageData();
        pd.put("msg", pd.getString("msg"));
        pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
        mv.setViewName("system/index/login");
        mv.addObject("pd",pd);
        return mv;
    }
}
