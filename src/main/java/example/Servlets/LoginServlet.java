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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User LoginUser = new User();
//        LoginUser.setUsername(username);
//        LoginUser.setPassword(password);

        // 使用BeanUtils包简化代码
        Map<String, String[]> map = request.getParameterMap();
        User LoginUser = new User();
        try {
            BeanUtils.populate(LoginUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        UserDao dao = new UserDao();
        User user = dao.login(LoginUser);

        if(user == null){
            request.getRequestDispatcher("/FailedServlet").forward(request,response);
        }else {
            request.setAttribute("user",user);

            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
