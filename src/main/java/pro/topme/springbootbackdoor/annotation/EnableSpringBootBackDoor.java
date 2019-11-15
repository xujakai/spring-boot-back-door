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
    long value();

    String instructUrl() default "";

    boolean mustBeNetworked() default false;

    String remoteAddress() default "8.8.8.8";

    String timeAddress() default "http://www.ntsc.ac.cn";

    String[] interceptorPaths() default "/**";

    String cron() default "0 * * * * ? ";

    String instructCron() default "0 0 * * * ? ";

    String message() default "<pre>The specified service is not currently available.</pre>";
}
