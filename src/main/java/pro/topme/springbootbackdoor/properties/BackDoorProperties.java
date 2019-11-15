package pro.topme.springbootbackdoor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author XuJiakai
 * @ClassName: BackDoorProperties
 * @Description:
 * @date 2019/11/15 17:07
 */
@Component
@ConfigurationProperties(prefix = "topme")
public class BackDoorProperties {

}
