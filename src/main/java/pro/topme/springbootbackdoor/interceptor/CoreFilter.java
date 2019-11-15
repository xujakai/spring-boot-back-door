package pro.topme.springbootbackdoor.interceptor;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import pro.topme.springbootbackdoor.properties.EnableSpringBootBackDoorCondition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XuJiakai
 * @ClassName: CoreFilter
 * @Description:
 * @date 2019/11/15 17:16
 */
@Configuration
@Conditional(EnableSpringBootBackDoorCondition.class)
public class CoreFilter  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}
