package pro.topme.springbootbackdoor.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XuJiakai
 * @ClassName: CoreFilter
 * @Description: 拦截
 * @date 2019/11/15 17:16
 */
public class CoreFilter implements HandlerInterceptor {
    public static boolean ACCESS = false;
    public String msg;

    public CoreFilter(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!ACCESS) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(msg);
        }
        return ACCESS;
    }
}
