package web.servlet;

import domain.PageBean;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindUserByPageServlet", value = "/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rows = request.getParameter("rows");
        String currentPage = request.getParameter("currentPage");

        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }

        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows);

        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/Case/list.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
