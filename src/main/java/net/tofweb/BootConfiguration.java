package net.tofweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@Configuration
@ComponentScan(basePackages = "net.tofweb")
public class BootConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(BootConfiguration.class, args);
	}

}
