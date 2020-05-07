package com.project.TINAClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;

@SpringBootApplication
public class TinaClientApplication {

	public static void main(String[] args) {

		SpringApplication.run(TinaClientApplication.class, args);
		//Client client = new Client("127.0.0.1", 5000);

	}

	@Bean
	public RSocketRequester rSocketRequester(RSocketRequester.Builder rSocketRequesterBuilder) {
		return rSocketRequesterBuilder
				.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
				.connectTcp("localhost",7000).block();
	}

}
