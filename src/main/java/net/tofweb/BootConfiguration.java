package net.tofweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lynn Owens
 *
 *         Bootstrap the application
 */
// This class contains configuration info
@Configuration
// Prevents ClassCastException with EntityManagerHolder
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
// Scan all of the app for components
@ComponentScan(basePackages = "net.tofweb")
public class BootConfiguration {

	public static void main(String[] args) {
		// Standard log4j configuration is enough for the demo
		org.apache.log4j.BasicConfigurator.configure();
		SpringApplication.run(BootConfiguration.class, args);
	}

}
