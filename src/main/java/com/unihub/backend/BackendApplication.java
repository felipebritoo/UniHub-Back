package com.unihub.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class BackendApplication {

	private final Environment env;

	public BackendApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		String port = env.getProperty("server.port", "8080");
		String contextPath = env.getProperty("server.servlet.context-path", "");
		String host = "localhost";

		log.info("\n----------------------------------------------------------\n" +
				"\tSwagger UI: \thttp://{}:{}{}/swagger-ui/index.html\n" +
				"----------------------------------------------------------",
				host, port, contextPath);
	}

}
