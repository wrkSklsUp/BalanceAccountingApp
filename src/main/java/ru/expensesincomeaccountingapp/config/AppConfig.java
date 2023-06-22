package ru.expensesincomeaccountingapp.config;

import com.google.common.base.Preconditions;

import org.hibernate.jpa.HibernatePersistenceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@ComponentScan(basePackages = "ru.expensesincomeaccountingapp")
@SpringBootApplication
@PropertySource({"classpath:db.properties"})
public class AppConfig {

	@Autowired
	private Environment environment;

	public AppConfig() {
		super();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPackagesToScan("ru.expensesincomeaccountingapp.entity");
		entityManagerFactory.setDataSource(getDataSource());
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter());
		entityManagerFactory.setJpaProperties(additionalProperties());
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

		return entityManagerFactory;

	}

	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(environment.getProperty("jdbc.driver")));
		dataSource.setUrl(Preconditions.checkNotNull(environment.getProperty("jdbc.url")));
		dataSource.setUsername(Preconditions.checkNotNull(environment.getProperty("jdbc.user")));
		dataSource.setPassword(Preconditions.checkNotNull(environment.getProperty("jdbc.password")));

		return dataSource;
	}

	@Bean
	public JpaVendorAdapter vendorAdapter(){
		return new HibernateJpaVendorAdapter();
	}

	Properties additionalProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql","true");
		properties.setProperty("hibernate.format_sql","true");

		return properties;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		return transactionManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}
