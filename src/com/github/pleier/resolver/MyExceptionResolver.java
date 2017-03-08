package com.github.pleier.resolver;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by PLEI on 2/9/2017.
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("==============异常开始=============");
        e.printStackTrace();
        System.out.println("==============异常结束=============");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e.toString().replaceAll("\n", "<br/>"));
        return mv;
    }
}
