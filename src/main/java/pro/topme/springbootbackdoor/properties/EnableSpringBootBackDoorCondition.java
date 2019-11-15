package pro.topme.springbootbackdoor.properties;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import pro.topme.springbootbackdoor.annotation.EnableSpringBootBackDoor;

/**
 * @author XuJiakai
 * @ClassName: EnableSpringBootBackDoorCondition
 * @Description:
 * @date 2019/11/15 17:27
 */
public class EnableSpringBootBackDoorCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String[] enablers = context.getBeanFactory()
                .getBeanNamesForAnnotation(EnableSpringBootBackDoor.class);
        ConditionMessage.Builder message = ConditionMessage
                .forCondition("@EnableSpringBootBackDoor Condition");
        if (enablers != null && enablers.length > 0) {
            return ConditionOutcome.match(message
                    .found("@EnableSpringBootBackDoor annotation on Application")
                    .items(enablers));
        }

        return ConditionOutcome.noMatch(message.didNotFind(
                "@EnableSpringBootBackDoor annotation " + "on Application")
                .atAll());
    }
}
