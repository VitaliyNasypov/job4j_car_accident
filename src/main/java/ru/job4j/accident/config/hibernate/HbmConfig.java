package ru.job4j.accident.config.hibernate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class HbmConfig {

    @Bean
    public DataSource ds(@Value("${jdbc.driver}") String driver,
                         @Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        hibernateProp.put("hibernate.format sql", true);
        hibernateProp.put("hibernate.use sql comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.hbm2ddl.auto", "update");
        hibernateProp.put("hibernate.jdbc.time_zone", "UTC");
        hibernateProp.put("hibernate.max fetch_depth", 3);
        hibernateProp.put("hibernate. jdbc.batch size", 10);
        hibernateProp.put("hibernate.jdbc. fetch_size", 50);
        return hibernateProp;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan("ru.job4j.accident.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager htx(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }
}
