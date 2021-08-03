package by.it_academy.jd2.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by Vitali Tsvirko
 */
@Configuration
@EnableJpaRepositories("by.it_academy.jd2.repository")
@EnableTransactionManagement
@PropertySource("classpath:datasource.properties")
public class PersistentConfig {

    private final Environment env;

    public PersistentConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds;

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(env.getProperty("db.driver"));
        cpds.setJdbcUrl(env.getProperty("db.url"));
        cpds.setUser(env.getProperty("db.user"));
        cpds.setPassword(env.getProperty("db.password"));
        cpds.setMinPoolSize(env.getProperty("connectionPoolCreator.minPoolSize", Integer.class));
        cpds.setAcquireIncrement(env.getProperty("connectionPoolCreator.acquireIncrement", Integer.class));
        cpds.setMaxPoolSize(env.getProperty("connectionPoolCreator.maxPoolSize", Integer.class));
        cpds.setMaxStatements(env.getProperty("connectionPoolCreator.maxStatements", Integer.class));

        return cpds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("by.it_academy.jd2.domain");
        factory.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.default_schema", env.getProperty("hibernate.default_schema"));

        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
