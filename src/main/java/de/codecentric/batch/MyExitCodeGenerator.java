package de.codecentric.batch;

import static org.springframework.batch.core.BatchStatus.COMPLETED;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyExitCodeGenerator implements ApplicationListener<JobExecutionEvent>, ExitCodeGenerator {
	private final List<JobExecution> executions = new ArrayList<JobExecution>();

	@Override
	public void onApplicationEvent(JobExecutionEvent event) {
		this.executions.add(event.getJobExecution());
	}

	@Override
	public int getExitCode() {
		for (JobExecution execution : this.executions) {
			if (!execution.getStatus().equals(COMPLETED)) {
				for (StepExecution stepExecution : execution.getStepExecutions()) {
					if (stepExecution.getFailureExceptions().size() > 0) {
						if (stepExecution.getFailureExceptions().get(0) instanceof IllegalArgumentException) {
							return 12;
						}
						if (stepExecution.getFailureExceptions().get(0) instanceof RuntimeException) {
							return 10;
						}
						return 1;
					}
				}
			}
		}
		return 0;
	}
}
