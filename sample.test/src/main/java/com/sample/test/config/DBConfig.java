package com.sample.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.ejb.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS;

/**
 * Created by andongxu on 16-6-14.
 */
//@Configuration
//@PropertySource("classpath:db.properties")
public class DBConfig {

    @Autowired
    protected Environment env;
    public static final String UNDEFINED = "**UNDEFINED**";
    public static final String CONNECTION_CHAR_SET = "hibernate.connection.charSet";

//    @Value("#{ env['entity.package'] }")
    private String entityPackage;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
//        vendorAdapter.setShowSql(true);
        factory.setPackagesToScan(entityPackage);
        factory.setDataSource(dataSource);

        factory.setJpaProperties(getJpaProperties());
                factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO, UNDEFINED));
        properties.setProperty(GENERATE_STATISTICS, env.getProperty(GENERATE_STATISTICS, UNDEFINED));
        properties.setProperty(SHOW_SQL, env.getProperty(SHOW_SQL, "FALSE"));
        properties.setProperty(FORMAT_SQL, env.getProperty(FORMAT_SQL, "FALSE"));
        properties.setProperty(USE_SQL_COMMENTS, env.getProperty(USE_SQL_COMMENTS, UNDEFINED));
        properties.setProperty(CONNECTION_CHAR_SET, env.getProperty(CONNECTION_CHAR_SET, UNDEFINED));
        properties.setProperty(AvailableSettings.NAMING_STRATEGY, ImprovedNamingStrategy.class.getName());
        return properties;
    }

//    @Override
    protected Class<? extends Dialect> getDatabaseDialect() {
        return MySQL5InnoDBDialect.class;
    }

    @Bean
    public DatabasePopulator databasePopulator() {
        return null;
    }
}
