package com.project.TinaV2;

import com.project.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.project.services.repositories")
@EntityScan(basePackages = {"com.project.entities"})
@ComponentScan(basePackages = {"com.project"})
public class TinAv2Application {

	public static void main(String[] args) {

		//Application.launch(JavaFxApplication.class, args);

		//Server server = new Server(5000);

		SpringApplication.run(TinAv2Application.class, args);
	}

}
