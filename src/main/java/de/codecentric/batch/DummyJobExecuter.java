package de.codecentric.batch;

import static org.springframework.boot.SpringApplication.exit;
import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DummyJobExecuter {

	public static void main(String[] args) {
		ApplicationContext ctx = run(DummyJobExecuter.class, args);
		//hier kommt MyExitCodeGenerator zurück
		ExitCodeGenerator exitCodeGenerator = ctx.getBean(ExitCodeGenerator.class);
		int exitCode = exit(ctx, exitCodeGenerator);
		System.exit(exitCode);
	}
}
