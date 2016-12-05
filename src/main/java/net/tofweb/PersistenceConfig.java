package net.tofweb;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lynn Owens
 *
 *         Setup the persistence framework
 */
// This class contains configuration info
@Configuration
// Permit the use of transactions for db connections
@EnableTransactionManagement
public class PersistenceConfig {

	@Resource
	private Environment env;

	/**
	 * Get a SessionFactory with which to build sessions to database
	 * 
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "net.tofweb" });
		sessionFactory.setHibernateProperties(buildHibernateProperties());

		return sessionFactory;
	}

	/**
	 * Get a DataSource to the embedded H2 database
	 * 
	 * @return EmbeddedDatabase
	 */
	@Bean
	public DataSource getDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("db/sql/initDb.sql").build();
		return db;
	}

	/**
	 * Get a TransactionManager using the specified SessionFactory
	 * 
	 * @param sessionFactory
	 * @return HibernateTransactionManager
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	/**
	 * Get a translator to convert database driver exceptions into a common
	 * standard
	 * 
	 * @return PersistenceExceptionTranslationPostProcessor
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/*
	 * Build the Hibernate Properties. This configures Hibernate to talk to the
	 * H2 database.
	 */
	private Properties buildHibernateProperties() {
		return new Properties() {

			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			}
		};
	}
}
