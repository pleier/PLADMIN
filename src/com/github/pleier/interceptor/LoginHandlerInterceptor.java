package com.github.pleier.interceptor;

import com.github.pleier.entity.system.User;
import com.github.pleier.util.Const;
import com.github.pleier.util.Jurisdiction;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆过滤\权限验证
 * Created by PLEI on 2/9/2017.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if(path.matches(Const.NO_INTERCEPTOR_PATH)){
            return true;
        }else{
            User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
            if(user!=null){
                path = path.substring(1, path.length());
                boolean b = Jurisdiction.hasJurisdiction(path); //访问权限校验
                if(!b){
                    response.sendRedirect(request.getContextPath() + Const.LOGIN);
                }
                return b;
            }else{
                //登陆过滤
                response.sendRedirect(request.getContextPath() + Const.LOGIN);
                return false;
            }
        }
    }
}
