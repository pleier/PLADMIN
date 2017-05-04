package com.github.pleier.controller.system.base;

import com.github.pleier.entity.Page;
import com.github.pleier.util.Logger;
import com.github.pleier.util.PageData;
import com.github.pleier.util.UuidUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PLEI on 2/10/2017.
 */
public class BaseController{
    protected Logger logger = Logger.getLogger(this.getClass());

    /** new PageData对象
     * @return
     */
    public PageData getPageData(){
        return new PageData(this.getRequest());
    }

    /**得到ModelAndView
     * @return
     */
    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    /**得到request对象
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**得到32位的uuid
     * @return
     */
    public String get32UUID(){
        return UuidUtil.get32UUID();
    }

    /**得到分页列表的信息
     * @return
     */
    public Page getPage(){
        return new Page();
    }

    public static void logBefore(Logger logger, String interfaceName){
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    public static void logAfter(Logger logger){
        logger.info("end");
        logger.info("");
    }

}
