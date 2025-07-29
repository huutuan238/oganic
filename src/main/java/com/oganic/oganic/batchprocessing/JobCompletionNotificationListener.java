package com.oganic.oganic.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Component;

import com.oganic.oganic.product.Product;

import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	private final JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			jdbcTemplate.query("SELECT * FROM product", new DataClassRowMapper<>(Product.class))
					.forEach(person -> log.info("Found <{}> in the database.", person));
		}
	}
}
