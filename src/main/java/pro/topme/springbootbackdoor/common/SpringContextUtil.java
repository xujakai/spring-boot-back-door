package pro.topme.springbootbackdoor.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author XuJiakai
 * @ClassName: SpringContextUtil
 * @Description:
 * @date 2019/11/15 17:10
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static String applicationName;
    private static String port;


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getPort() {
        return port;
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<?> clz) throws BeansException {
        return (T) applicationContext.getBean(clz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        SpringContextUtil.applicationName = applicationContext.getId();
        SpringContextUtil.port = applicationContext.getEnvironment().getProperty("server.port");
        SpringContextUtil.port = SpringContextUtil.port == null ? "8080" : SpringContextUtil.port;
    }
}

