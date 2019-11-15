package pro.topme.springbootbackdoor.scheduler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import pro.topme.springbootbackdoor.interceptor.CoreFilter;
import pro.topme.springbootbackdoor.utils.NetWorkUtils;

import java.util.Date;

/**
 * @author XuJiakai
 * @ClassName: SpringBootBackDoorScheduler
 * @Description:
 * @date 2019/11/15 18:12
 */
@Slf4j
@Getter
@Setter
public class SpringBootBackDoorScheduler implements SchedulingConfigurer {
    private long timestamp;
    private String instructUrl;
    private String cron;
    private NetWorkUtils netWorkUtils;
    private Boolean mustBeNetworked;

    private Runnable task() {
        return () -> {
            boolean reachable = netWorkUtils.isReachable();
            if (reachable) {
                log.debug("Network available");
            } else {
                log.warn("Network unavailable");
                if (mustBeNetworked) {
                    log.error("must be networked,Please check the network connection!");
                    System.exit(-1);
                }
            }
            try {
                Long websiteDatetime = netWorkUtils.getCurrentTime(reachable);
                if (websiteDatetime == null) {
                    log.error("must be networked,Please check the network connection!");
                    System.exit(-1);
                }
                CoreFilter.ACCESS = websiteDatetime <= timestamp;
            } catch (Exception e) {
                e.printStackTrace();
                CoreFilter.ACCESS = false;
            }
        };
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        try {
            boolean reachable = netWorkUtils.isReachable();
            if (!reachable && mustBeNetworked) {
                log.error("must be networked,Please check the network connection!");
                System.exit(-1);
            }
            Long websiteDatetime = netWorkUtils.getCurrentTime(reachable);
            CoreFilter.ACCESS = websiteDatetime <= timestamp;
        } catch (Exception e) {
            CoreFilter.ACCESS = false;
        }
        scheduledTaskRegistrar.addTriggerTask(task(), triggerContext -> {
            log.debug("reset scheduled task");
            if ("".equals(cron) || cron == null) {
                return null;
            }
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });
    }
}
