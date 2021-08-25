package example.Servlets;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckCodeServlet", value = "/check")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        int style = 30;
        int size = 25;
        final String codes = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final String checkCodeName = "checkCode";
        // 实例化图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 美化图片
        Graphics g = image.getGraphics();
        g.setFont(new Font(Font.DIALOG,style,size));
        // 设置验证码边框样式
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);

        Random random = new Random();
        // 为了结合login将验证码存储到Session中
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(codes.length());
            char ch = codes.charAt(index);
            g.drawString(ch + "", width * 10 / 55 * (i+1), height / 3 * 2);
            sb.append(ch);
        }
        // 通过Session共享传递code值到LoginCheckCodeServlet判断
        String code = sb.toString();
        request.getSession().setAttribute(checkCodeName,code);

        //画干扰线
        g.setColor(Color.GREEN);

        for (int i = 0; i < 6; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        // 输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
