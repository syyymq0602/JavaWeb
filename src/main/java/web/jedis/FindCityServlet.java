package web.jedis;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.City;
import service.CityService;
import service.impl.CityServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindCityServlet", value = "/findCityServlet")
public class FindCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        CityService service = new CityServiceImpl();
//        List<City> cities = service.findAll();
//        // 使用json序列化数据
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(cities);
        String json = service.findAllJson();
        // 响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
