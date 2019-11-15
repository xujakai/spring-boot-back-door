package pro.topme.springbootbackdoor.annotation;

import org.springframework.context.annotation.Import;
import pro.topme.springbootbackdoor.properties.BackDoorProperties;

import java.lang.annotation.*;


/**
 * @author XuJiakai
 * @ClassName: EnableSpringBootBackDoor
 * @Description:
 * @date 2019/11/15 17:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
@Import(BackDoorProperties.class)
public @interface EnableSpringBootBackDoor {
}
