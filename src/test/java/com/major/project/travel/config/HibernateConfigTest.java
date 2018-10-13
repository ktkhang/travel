package com.major.project.travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by HUY on 10/3/2018
 */
@Configuration
@Profile("testProfile")
@EnableTransactionManagement
public class HibernateConfigTest {

    @Autowired
    private ApplicationContext context;

    //
    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        sessionFactoryBean.setPackagesToScan("com.major.project.travel.model");
        return sessionFactoryBean;
    }

    //
    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactoryBean().getObject());
        return transactionManager;
    }

}
