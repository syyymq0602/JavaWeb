package example.Servlets;

import example.Dao.UserDao;
import example.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginCheckCodeServlet", value = "/login")
public class LoginCheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        Map<String, String[]> map = request.getParameterMap();
        // 使用BeanUtils包简化代码
        User LoginUser = new User();
        try {
            BeanUtils.populate(LoginUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 获取request中的验证码的值,如果没有则为null
        final String checkCodeName = "checkCode";
        String check = map.get(checkCodeName)[0];
        // 获取Session的验证码的值
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(checkCodeName);
        // 清除Session的验证码缓存
        session.removeAttribute(checkCodeName);

        if (code != null && code.equalsIgnoreCase(check)) {
            // 验证相等的话再验证用户
            UserDao dao = new UserDao();
            User user = dao.login(LoginUser);

            if (user == null) {
                request.setAttribute("login_error", "用户名或者密码错误！");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                session.setAttribute("user", user.getUsername());

                response.sendRedirect(request.getContextPath() + "/JSP/success.jsp");
            }
        } else {
            // 验证码不一致或者没有传验证码
            // 存储提示信息到页面
            request.setAttribute("error", "验证码错误！");
            // 跳转到登录页面
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
