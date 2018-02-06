package tjc.lucien.intelligen.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2017/3/20.
 */
@Component
@WebListener
public class WebContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = context.getContextPath();
        System.out.println("根路径：" + path);
        context.setAttribute("ctx",path);
        context.setAttribute("pic_base",path+"/img/");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
