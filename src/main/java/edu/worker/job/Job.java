package edu.worker.job;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Component
@DisallowConcurrentExecution
public class Job extends QuartzJobBean {

    /**
     * The fake job for testing the autoscaling behavior
     *
     * @param context - job context
     * @throws JobExecutionException
     */
    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String id = context.getJobDetail().getKey().getName();
        log.info("begin {}", id);
        TimeUnit.SECONDS.sleep(1);
        log.info("end {}", id);
    }
}
