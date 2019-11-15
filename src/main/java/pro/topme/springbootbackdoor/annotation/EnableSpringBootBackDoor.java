package pro.topme.springbootbackdoor.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import pro.topme.springbootbackdoor.SpringBootBackDoorRegistrar;

import java.lang.annotation.*;


/**
 * @author XuJiakai
 * @ClassName: EnableSpringBootBackDoor
 * @Description: 建议将配置写入代码，以防止配置文件被篡改
 * @date 2019/11/15 17:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableScheduling
@Import(SpringBootBackDoorRegistrar.class)
public @interface EnableSpringBootBackDoor {
    /**
     * 截止日期时间戳，在指定时间戳内允许访问接口
     *
     * @return
     */
    long value();

    String instructUrl() default "";

    /**
     * 是否强制项目联网
     *
     * @return
     */
    boolean mustBeNetworked() default false;

    /**
     * 判断是否联网地址，暂不可使用域名形式
     *
     * @return
     */
    String remoteAddress() default "8.8.8.8";

    /**
     * 获取网络时间地址
     *
     * @return
     */
    String timeAddress() default "http://www.ntsc.ac.cn";

    /**
     * 默认拦截接口
     *
     * @return
     */
    String[] interceptorPaths() default "/**";

    /**
     * 校验是否允许访问定时器
     *
     * @return
     */
    String cron() default "0 * * * * ? ";

    String instructCron() default "0 0 * * * ? ";

    /**
     * 拦截后自定义返回信息
     *
     * @return
     */
    String message() default "<pre>The specified service is not currently available.</pre>";

    /**
     * 临时文件名
     *
     * @return
     */
    String fileName() default "open.api.ini";
}
