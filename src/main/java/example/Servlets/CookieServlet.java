package example.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookieServlet", value = "/cookie")
public class CookieServlet extends HttpServlet {

    private final String cookieName = "lastTime";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置相应文本样式
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (cookieName.equals(name)) {
                    flag = true;
                    String value = cookie.getValue();

                    // Encoder编码，Decoder解码
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，你上次的访问时间为" + value + "</h1>");

                    // 重新设置Cookie值并发送
                    SetCookie(cookie, response);

                    break;
                }
            }
        }

        if (cookies == null || cookies.length == 0 || !flag) {
            SetCookie(null, response);
            response.getWriter().write("<h1>你好，欢迎你首次访问</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    // 抽象设置Cookie方法
    private void SetCookie(Cookie cookie, HttpServletResponse response) throws UnsupportedEncodingException {
        String str_time = new SimpleDateFormat("yyyy年MM月dd日 HH小时mm分ss秒")
                .format(new Date());
        str_time = URLEncoder.encode(str_time, "utf-8");
        cookie = cookie == null ? new Cookie(cookieName, str_time) : cookie;
        cookie.setValue(str_time);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }
}
