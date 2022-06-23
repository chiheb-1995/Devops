package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"tn.esprit.spring.entities"})
public class TimesheetDevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetDevopsApplication.class, args);
	}

}
