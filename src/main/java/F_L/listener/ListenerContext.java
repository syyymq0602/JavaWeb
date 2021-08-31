package F_L.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;

@WebListener
public class ListenerContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Listener has initialized......");
        // 获取对象
        ServletContext servletContext = sce.getServletContext();
        // 加载资源文件
        String parameter = servletContext.getInitParameter("contextConfigLocation");
        // 获取真实路径
        String path = servletContext.getRealPath(parameter);
        System.out.println(path);
        // 加载进内存
        try{
            FileInputStream fis = new FileInputStream(path);
            System.out.println(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Listener has been destroyed......");
    }
}
