package example.Servlets;

import example.util.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownLoadServlet", value = "/down")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取文件名
        String filename = request.getParameter("filename");
        // 使用字节流加载进内存
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/Blobs/" + filename);
        FileInputStream fis = new FileInputStream(path);
        // 设置response响应头
        String mimeType = context.getMimeType(filename);
        response.setHeader("content-type", mimeType);
        // 解决中文文件名问题
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        // 输入流输出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            sos.write(buff, 0, len);
        }

    }
}
