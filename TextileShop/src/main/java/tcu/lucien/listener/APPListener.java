package tcu.lucien.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2016/12/17.
 */
@Component
@WebListener
public class APPListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = context.getContextPath();
        context.setAttribute("ctx",path);
        context.setAttribute("pic_base",path+"/img/");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
