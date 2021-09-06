package com.cafe.managerassignment;

import com.cafe.managerassignment.config.ApplicationConfig;
import com.cafe.managerassignment.jwt.JwtConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

import static org.springframework.boot.SpringApplication.run;

@EntityScan("com.cafe.managerassignment.model")
@EnableJpaRepositories("com.cafe.managerassignment.repo")
@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class, ApplicationConfig.class})
public class ManagerAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run (ManagerAssignmentApplication.class, args);
//        SpringApplication application = new SpringApplication(ManagerAssignmentApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
    }
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

   /*
    public void setUp() {
        productRepository.save(new Product("Cola", "rtrtretert", 12.3456));
        productRepository.save(new Product("Pepsi", "ggfgdfgd", 23.4567));
        productRepository.save(new Product("Lemonad", "asdsdasd", 34.5678));
        productRepository.save(new Product("Bounty", "asdsa", 45.6789));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource("myDb");
        entityManagerFactoryBean.setPackagesToScan("com.cafe.managerassignment.model");
        //additional config of factory

        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) throws URISyntaxException {
        JpaTransactionManager transactionManager = new JpaTransactionManager ();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }*/
}

