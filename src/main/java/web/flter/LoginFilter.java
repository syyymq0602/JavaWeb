package web.flter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 强转成HttpServlet
        HttpServletRequest req = (HttpServletRequest) request;
        // 获取资源请求路径
        String uri = req.getRequestURI();
        // 判断是否包含登录相关资源路径，要排除静态资源图片
        if (uri.contains("/css/") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet")
                || uri.contains("/js/") || uri.contains("/login.jsp")) {
            chain.doFilter(request, response);
        } else {
            // 不包含先验证用户是否登录
            Object user = req.getSession().getAttribute("user");
            if (user != null) {
                // 若已经登录则直接放行
                chain.doFilter(request, response);
            } else {
                // 若没有登录则跳转到登录页面
                req.setAttribute("login_fail", "你尚未登录，请登录!");
                req.getRequestDispatcher("/Case/login.jsp").forward(req, response);
            }
        }
    }
}
