package edu.worker.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class QuartzMetricService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * get count of a triggers that are ready for start more than 5s but not started yet
     *
     * @param o never mind
     * @return quartz's cluster queue size
     */
    public double getQueueSize(Object o) {
        var queue = jdbcTemplate.query("SELECT count(*) from qrtz_triggers " +
                                "where  trigger_state='WAITING' and to_timestamp((next_fire_time+5000)/1000)<=CURRENT_TIMESTAMP",
                        (rs, rowNum) -> rs.getInt(1))
                .get(0);
        log.info("receive probe for quartz's cluster queue size:{}", queue);
        return queue;
    }
}
