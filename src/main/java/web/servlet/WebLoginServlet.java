package web.servlet;


import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "WebLoginServlet", value = "/loginServlet")
public class WebLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        final String verify = "verifyCode";
        final String check = "CHECKCODE_SERVER";
        // 获取表单传来的验证码
        String checkCode = request.getParameter(verify);
        // 校验验证码，若失败就不需要加载User信息，转发到login界面
        HttpSession session = request.getSession();
        String serverCode = (String) session.getAttribute(check);
        if (serverCode == null) {
            response.sendRedirect(request.getContextPath() + "/Case/indexDemo.jsp");
            return;
        }
        // 确保验证码是一次性
        session.removeAttribute(check);

        if (!serverCode.equalsIgnoreCase(checkCode)) {
            request.setAttribute("login_fail", "验证码错误!");
            request.getRequestDispatcher("/Case/login.jsp").forward(request, response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User LoginUser = new User();
        try {
            BeanUtils.populate(LoginUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        User loginUser = service.login(LoginUser);
        if (loginUser != null) {
            // 登录成功
            request.setAttribute("login_success", "登录成功！");
            session.setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/Case/indexDemo.jsp");
        } else {
            // 登陆失败
            request.setAttribute("login_fail", "用户名或密码错误!");
            request.getRequestDispatcher("/Case/login.jsp").forward(request, response);
        }
    }
}
