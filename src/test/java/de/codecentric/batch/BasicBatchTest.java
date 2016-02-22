package de.codecentric.batch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.batch.core.BatchStatus.COMPLETED;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DummyJobExecuter.class, BatchMockConfiguration.class})
@IntegrationTest("spring.batch.job.enabled=false")
public class BasicBatchTest {
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ItemReader<String> readerMock;
	
	@Test
	public void sollteBatchStarten() throws Exception {
		//given
		given(readerMock.read()).willReturn("Hello1", "Hello2", null);
		//when
		JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
		//then
		assertThat(jobExecution.getStatus(), is(COMPLETED));
		Iterator<StepExecution> steps = jobExecution.getStepExecutions().iterator();
		//init-step
		assertThat(steps.next().getCommitCount(), is(1));
		//dummy-step
		assertThat(steps.next().getCommitCount(), is(2));
	}
}
