package de.codecentric.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
    @Bean
    public DummyItemReader dummyReader() {
		return new DummyItemReader();
    }
    
    @Bean
    public LogItemProcessor dummyProcessor() {
        return new LogItemProcessor();
    }
    
    @Bean
    public LogItemWriter dummyWriter() {
    	return new LogItemWriter();
    }    

    @Bean
    public Job dummyJob(JobBuilderFactory jobs, Step dummyStep) {
		return jobs.get("dummyJob")
    			.incrementer(new RunIdIncrementer())
    			.flow(dummyStep)
    			.end()
    			.build();
    }    

    @Bean
    public Step dummyStep(StepBuilderFactory stepBuilderFactory, ItemReader<String> dummyReader,
    		ItemProcessor<Object, Object> dummyProcessor, ItemWriter<Object> dummyWriter) {
        return stepBuilderFactory.get("dummyStep")
                .<Object, Object>chunk(2)
                .reader(dummyReader)
                .processor(dummyProcessor)
                .writer(dummyWriter)
                .build();
    }
}