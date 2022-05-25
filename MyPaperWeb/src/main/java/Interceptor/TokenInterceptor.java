package Interceptor;

import JwtUtils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        try {
                JwtUtils.verify(token);
                response.sendRedirect("/login");
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mode){
        System.out.println("执行完了");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        System.out.println("获取到一个返回结果"+response);
        System.out.println("请求结束");
    }
}
