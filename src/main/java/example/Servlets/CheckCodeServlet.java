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
        final String codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // 实例化图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 美化图片
        Graphics g = image.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(codes.length());
            char ch = codes.charAt(index);
            g.drawString(ch + "", width / 6 * (i+1), height / 2);
        }


        //画干扰线
        g.setColor(Color.GREEN);

        for (int i = 0; i < 8; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        g.drawString("A", 20, 25);
        g.drawString("A", 40, 35);
        g.drawString("A", 60, 40);
        g.drawString("A", 80, 12);
        // 输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
