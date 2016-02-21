package de.codecentric.batch;

import static org.mockito.MockitoAnnotations.initMocks;

import javax.annotation.PostConstruct;

import org.mockito.Mock;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchTestConfiguration {
		@Mock
		private ItemReader<String> readerMock;
		
		@PostConstruct
		public void setUp() {
			initMocks(this);
		}
		
		@Bean
		public ItemReader<String> dummyReader() {
			return readerMock;
		}
}
