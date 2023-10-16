package com.accenture.horsefeeding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



/*@ComponentScan(basePackages = {"com.accenture.horsefeeding"})
@EntityScan("com.accenture.horsefeeding")
@EnableJpaRepositories("com.accenture.horsefeeding")*/
@SpringBootApplication
public class HorsefeedingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorsefeedingApplication.class, args);
	}

}
