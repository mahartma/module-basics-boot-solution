package de.codecentric.batch;

import static org.mockito.MockitoAnnotations.initMocks;

import javax.annotation.PostConstruct;

import org.mockito.Mock;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchMockConfiguration {
		@Mock
		private ItemReader<String> readerMock;

		@Mock
		private ItemWriter<Object> writerMock;
		
		@PostConstruct
		public void setUp() {
			initMocks(this);
		}
		
		@Bean
		public ItemReader<String> dummyReader() {
			return readerMock;
		}
		@Bean
		public ItemWriter<Object> dummyWriter() {
			return writerMock;
		}
}
