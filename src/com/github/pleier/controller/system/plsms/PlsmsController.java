package com.github.pleier.controller.system.plsms;

import com.github.pleier.controller.system.base.BaseController;
import com.github.pleier.entity.Page;
import com.github.pleier.service.system.plsms.PlsmsManager;
import com.github.pleier.util.AppUtil;
import com.github.pleier.util.DateUtil;
import com.github.pleier.util.Jurisdiction;
import com.github.pleier.util.PageData;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 站内信
 * Created by pleiyang@outlook.com on 2017/5/16.
 */
@Controller
@RequestMapping(value="/plsms")
public class PlsmsController extends BaseController{

    String menuUrl = "plsms/list.do"; //菜单地址(权限用)
    @Resource(name="plsmsService")
    private PlsmsManager plsmsService;

    /**
     * 发送站内信
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"发送站内信");
        PageData pageData = this.getPageData();
        Map<String,Object> map = new HashMap<String,Object>();
        List<PageData> pageDataList = new ArrayList<PageData>();
        String msg = "ok";		//发送状态
        int count = 0;			//统计发送成功条数
        int zcount = 0;			//理论条数
        String USERNAME = pageData.getString("USERNAME");				//对方用户名
        if(null != USERNAME && !"".equals(USERNAME)){
            USERNAME = USERNAME.replaceAll("；", ";");
            USERNAME = USERNAME.replaceAll(" ", "");
            String[] arrUSERNAME = USERNAME.split(";");
            zcount = arrUSERNAME.length;
            try {
                pageData.put("STATUS", "2");										//状态
                for(int i=0;i<arrUSERNAME.length;i++){
                    pageData.put("SANME_ID", this.get32UUID());					//共同ID
                    pageData.put("SEND_TIME", DateUtil.getTime());				//发送时间
                    pageData.put("FHSMS_ID", this.get32UUID());					//主键1
                    pageData.put("TYPE", "2");									//类型2：发信
                    pageData.put("FROM_USERNAME", Jurisdiction.getUsername());	//发信人
                    pageData.put("TO_USERNAME", arrUSERNAME[i]);					//收信人
                    plsmsService.save(pageData);									//存入发信
                    pageData.put("FHSMS_ID", this.get32UUID());					//主键2
                    pageData.put("TYPE", "1");									//类型1：收信
                    pageData.put("FROM_USERNAME", arrUSERNAME[i]);				//发信人
                    pageData.put("TO_USERNAME", Jurisdiction.getUsername());		//收信人
                    plsmsService.save(pageData);
                    count++;
                }
                msg = "ok";
            } catch (Exception e) {
                msg = "error";
            }
        }else{
            msg = "error";
        }
        pageData.put("msg", msg);
        pageData.put("count", count);						//成功数
        pageData.put("ecount", zcount-count);				//失败数
        pageDataList.add(pageData);
        map.put("list", pageDataList);
        return AppUtil.returnObject(pageData, map);
    }

    /**删除
     * @param out
     * @throws Exception
     */
    @RequestMapping(value="/delete")
    public void delete(PrintWriter out) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除Plsms");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pageData = this.getPageData();
        plsmsService.delete(pageData);
        out.write("success");
        out.close();
    }

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Plsms");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String keywords = pd.getString("keywords");				//关键词检索条件
        if(null != keywords && !"".equals(keywords)){
            pd.put("keywords", keywords.trim());
        }
        String lastLoginStart = pd.getString("lastLoginStart");	//开始时间
        String lastLoginEnd = pd.getString("lastLoginEnd");		//结束时间
        if(lastLoginStart != null && !"".equals(lastLoginStart)){
            pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
        }
        if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
            pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
        }
        if(!"2".equals(pd.getString("TYPE"))){					//1：收信箱 2：发信箱
            pd.put("TYPE", 1);
        }
        pd.put("FROM_USERNAME", Jurisdiction.getUsername()); 	//当前用户名
        page.setPageData(pd);
        List<PageData>	varList = plsmsService.list(page);		//列出Plsms列表
        mv.setViewName("system/plsms/plsms_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());				//按钮权限
        return mv;
    }

    /**去发站内信界面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goAdd")
    public ModelAndView goAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("system/fhsms/fhsms_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        return mv;
    }

    /**去查看页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goView")
    public ModelAndView goView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        if("1".equals(pd.getString("TYPE")) && "2".equals(pd.getString("STATUS"))){ //在收信箱里面查看未读的站内信时去数据库改变未读状态为已读
            plsmsService.edit(pd);
        }
        pd = plsmsService.findById(pd);	//根据ID读取
        mv.setViewName("system/plsms/plsms_view");
        mv.addObject("pd", pd);
        return mv;
    }

    /**批量删除
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"批量删除Fhsms");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            plsmsService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
    }
}
