package edu.worker.config;

import edu.worker.service.QuartzMetricService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QuartzMetric {
    private final MeterRegistry meterRegistry;
    private final QuartzMetricService quartzMetricService;

    @Bean
    public Gauge queueSize() {
        return Gauge
                .builder("quartz.queue", quartzMetricService, quartzMetricService::getQueueSize)
                .description("Quartz's cluster queue size")
                .baseUnit("meters")
                .register(meterRegistry);
    }

}
