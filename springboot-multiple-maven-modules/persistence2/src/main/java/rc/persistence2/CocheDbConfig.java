package rc.persistence2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cocheEntityManagerFactory", transactionManagerRef = "cocheTransactionManager", basePackages = {
		"rc.repository2" }) //Mirar si se puede sustituir por rc.domain o rc.repository
public class CocheDbConfig {

	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "cocheDataSource")
	@ConfigurationProperties(prefix = "coches.datasource")
	public DataSource customDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("coches.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("coches.datasource.url"));
		dataSource.setUsername(env.getProperty("coches.datasource.username"));
		dataSource.setPassword(env.getProperty("coches.datasource.password"));
		return dataSource;
	}

	@Primary
	@Bean(name = "cocheEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("cocheDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("rc.domain2")
				.persistenceUnit("coche").build();
	}

	@Primary
	@Bean(name = "cocheTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("cocheEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}