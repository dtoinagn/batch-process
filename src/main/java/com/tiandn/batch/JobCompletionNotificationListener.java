package com.tiandn.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection of JdbcTemplate
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job started: " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job finished: " + jobExecution.getJobInstance().getJobName());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job completed successfully");
            jdbcTemplate.query("SELECT first_name, last_name FROM people",
                    (rs, row) -> new Person(rs.getString(1), rs.getString(2)))
                    .forEach(person -> log.info("Found <" + person + "> in the database."));
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.error("Job failed with status: " + jobExecution.getStatus());
        }
    }
}
