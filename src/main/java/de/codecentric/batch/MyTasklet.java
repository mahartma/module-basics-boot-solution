package de.codecentric.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTasklet {

	private static final Logger LOG = LoggerFactory.getLogger(MyTasklet.class);
	
	public void execute() {
		LOG.info("Hello Tasklet!");
	}
}
