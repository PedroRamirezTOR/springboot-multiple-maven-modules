package rc.persistence;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "hotelEntityManagerFactory", transactionManagerRef = "hotelTransactionManager", basePackages = {
		"rc.repository" }) //Mirar si se puede sustituir por rc.domain o rc.repository
public class HotelDbConfig {

	@Autowired
	private Environment env;

	@Bean(name = "hotelDataSource")
	@ConfigurationProperties(prefix = "hoteles.datasource")
	public DataSource customDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("hoteles.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("hoteles.datasource.url"));
		dataSource.setUsername(env.getProperty("hoteles.datasource.username"));
		dataSource.setPassword(env.getProperty("hoteles.datasource.password"));
		return dataSource;
	}

	@Bean(name = "hotelEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("hotelDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("rc.domain")
				.persistenceUnit("hotel").build();
	}

	@Bean(name = "hotelTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("hotelEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}