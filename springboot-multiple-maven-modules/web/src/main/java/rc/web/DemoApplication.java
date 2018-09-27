package rc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"rc.repository","rc.repository2"})
@EntityScan(basePackages= {"rc.domain","rc.domain2"})
@ComponentScan(basePackages= {"rc"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}
	
}
