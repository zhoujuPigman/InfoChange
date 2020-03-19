package cn.huanji.component;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理跨域传参
 * @author 猪肉佬
 */
@Component
public class CrossFiilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String curOrigin = httpServletRequest.getHeader("Origin");
        System.out.println("##跨域过滤器>>>>当前访问来源>>>>>>"+curOrigin+"##");

        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods","*");
        httpServletResponse.setHeader("Access-Control-Max-Age","3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","x-requested-with,origin,Content-Type,Accept,Authorization");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
