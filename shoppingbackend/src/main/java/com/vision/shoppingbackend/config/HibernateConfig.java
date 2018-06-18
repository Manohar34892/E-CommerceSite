package com.vision.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.vision.shoppingbackend.dao")
@EnableTransactionManagement
public class HibernateConfig {

	//Db final varible
	private final static String DATABASE_URL="jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER="org.h2.Driver";
	private final static String DATABASE_USERNAME="test";
	private final static String DATABASE_PASSWORD="test";
	private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	
	//Creation Commons CoonPoll DataSource
	@Bean
	public DataSource getDataSource() {
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	//Creating Hibernate SessionFactory
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder= new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.vision.shoppingbackend.dto");
		return builder.buildSessionFactory();
				
	}
	//Creating Hibernate Properties
	private Properties getHibernateProperties() {
		
		Properties properties= new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.format_sql","true");
		properties.put("hibernate.hbm2ddl.auto","update");
		return properties;
	}
	
	// create a transtionManager
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager= new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
}
