package pro.topme.springbootbackdoor.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author XuJiakai
 * @ClassName: SpringBootBackDoorFilterConfiguration
 * @Description: 拦截器配置
 * @date 2019/11/15 20:09
 */
@Getter
@Setter
public class SpringBootBackDoorFilterConfiguration implements WebMvcConfigurer {
    private List<String> interceptorPaths;
    public String msg;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new CoreFilter(msg));
        if (interceptorPaths == null || interceptorPaths.isEmpty()) {
            interceptorRegistration.addPathPatterns("/**");
        } else {
            interceptorRegistration.addPathPatterns(interceptorPaths);
        }
    }
}
