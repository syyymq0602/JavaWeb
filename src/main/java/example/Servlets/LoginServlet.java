package example.Servlets;

import example.Dao.UserDao;
import example.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User LoginUser = new User();
        LoginUser.setUsername(username);
        LoginUser.setPassword(password);

        UserDao dao = new UserDao();
        User user = dao.login(LoginUser);

        if(user == null){
            request.getRequestDispatcher("/FailedServlet").forward(request,response);
        }else {
            request.setAttribute("username",username);
            request.setAttribute("password",password);

            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
