import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import tjc.lucien.intelligen.config.AppConfig;
import tjc.lucien.intelligen.config.WebConfig;
import tjc.lucien.intelligen.controller.HomePageController;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class, HomePageController.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("UTF-8")};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.addMapping("/");
        registration.setMultipartConfig(new MultipartConfigElement("/",4194304,4194304,0));
    }
}
