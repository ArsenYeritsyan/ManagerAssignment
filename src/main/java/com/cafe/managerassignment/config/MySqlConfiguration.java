/* package com.cafe.managerassignment.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
/*
@EntityScan(basePackages = "com.cafe.managerassignment.model")
@EnableJpaRepositories(basePackages = "com.cafe.managerassignment.repo",
        entityManagerFactoryRef = "entityManagerMySQL", transactionManagerRef = "mysqlTxManager")
@EnableTransactionManagement
@EnableJpaAuditing
public class MySqlConfiguration {
    private static final String MYSQL_PERSISTENCE_UNIT_NAME = "MYSQLUNIT";
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.fetchSize}")
    private Integer fetchSize;
    @Value("${mysql.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${mysql.hibernate.show.sql}")
    private Boolean hibernateShowSql;
    @Value("${mysql.hibernate.format.sql}")
    private String hibernateFormatSql;
    @Value("${mysql.hibernate.validation.insert_group}")
    private String hibernateValidationInsertGroup;
    @Value("${mysql.hibernate.validation.update_group}")
    private String hibernateValidationUpdateGroup;
    @Value("${mysql.hibernate.validation.delete_group}")
    private String hibernateValidationDeleteGroup;
    @Value("${mysql.hibernate.use.second.level.cache}")
    private String hibernateUseSecondLevelCache;
    @Value("${mysql.hibernate.use.query.cache}")
    private String hibernateUseQueryCache;
    @Value("${mysql.hibernate.generate_statistics}")
    private String hibernateGenerateStatistics;
    @Value("${mysql.hibernate.cache.use_structured_entries}")
    private String hibernateCacheUseStructuredEntries;
    @Value("${mysql.hibernate.use_sql_comments}")
    private String hibernateUseSqlComments;
    @Value("${mysql.hibernate.listeners.envers.auto_register}")
    private String hibernateListenersEnversAutoRegister;

    @PersistenceContext(unitName = "mysqlContext")
    @Bean(name = "entityManagerMySQL")
    public LocalContainerEntityManagerFactoryBean entityManagerMySQL() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(mysqlDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.setPersistenceUnitManager(mySqlPersistenceUnitManager());
        entityManagerFactoryBean.setPersistenceUnitName(MYSQL_PERSISTENCE_UNIT_NAME);
        return entityManagerFactoryBean;
    }


    @Bean(name = "mysqlTxManager")
    public PlatformTransactionManager mysqlTxManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerMySQL().getObject());
        jpaTransactionManager.setDataSource(mysqlDataSource());
        return jpaTransactionManager;
    }


    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setDatabasePlatform(this.hibernateDialect);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(this.hibernateShowSql);
        return vendorAdapter;
    }

    @Bean
    public PersistenceUnitManager mySqlPersistenceUnitManager() {
        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
        persistenceUnitManager.setPackagesToScan("com.cafe.managerassignment.model");
        persistenceUnitManager.setDefaultDataSource(mysqlDataSource());
        persistenceUnitManager.setDefaultPersistenceUnitName(MYSQL_PERSISTENCE_UNIT_NAME);
        return persistenceUnitManager;
    }


    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    private Properties getHibernateProperties() {
        Properties hibernateProps = new Properties();
        hibernateProps.put(GroupsPerOperation.Operation.INSERT.getGroupPropertyName(), hibernateValidationInsertGroup);
        hibernateProps.put(GroupsPerOperation.Operation.UPDATE.getGroupPropertyName(), hibernateValidationUpdateGroup);
        hibernateProps.put(GroupsPerOperation.Operation.DELETE.getGroupPropertyName(), hibernateValidationDeleteGroup);

        hibernateProps.put("hibernate.enable_lazy_load_no_trans", true);
        hibernateProps.put("hibernate.id.new_generator_mappings", false);
        hibernateProps.put("hibernate.dialect", hibernateDialect);
        hibernateProps.put("hibernate.query.substitutions", "true 'Y', false 'N'");
        hibernateProps.put("hibernate.cache.use_second_level_cache", hibernateUseSecondLevelCache);
        hibernateProps.put("hibernate.cache.use_query_cache", hibernateUseQueryCache);
        hibernateProps.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
//        hibernateProps.put("net.sf.ehcache.configurationResourceName", hibernateEhcacheConfigurationFile);
        hibernateProps.put("hibernate.generate_statistics", hibernateGenerateStatistics);
        hibernateProps.put("hibernate.cache.use_structured_entries", hibernateCacheUseStructuredEntries);
        hibernateProps.put("hibernate.show_sql", hibernateShowSql);
        hibernateProps.put("hibernate.format_sql", hibernateFormatSql);
        hibernateProps.put("hibernate.use_sql_comments", hibernateUseSqlComments);
        hibernateProps.put("hibernate.listeners.envers.autoRegister", hibernateListenersEnversAutoRegister);
        hibernateProps.put("jadira.usertype.autoRegisterUserTypes", "true");
        hibernateProps.put("jadira.usertype.databaseZone", "jvm");

        hibernateProps.put("hibernate.c3p0.min_size", 5);
        hibernateProps.put("hibernate.c3p0.max_size", 20);
        hibernateProps.put("hibernate.c3p0.timeout", 300);
        hibernateProps.put("hibernate.c3p0.max_statements", 50);
        hibernateProps.put("hibernate.c3p0.idle_test_period", 3000);

        hibernateProps.put("hibernate.c3p0.validationQuery", "SELECT 1");
        hibernateProps.put("hibernate.c3p0.maxIdle", 5);
        hibernateProps.put("hibernate.c3p0.maxActive", 20);
        hibernateProps.put("hibernate.c3p0.initialSize", 5);
        hibernateProps.put("hibernate.c3p0.testWhileIdle", true);
        hibernateProps.put("hibernate.c3p0.preferredTestQuery", "SELECT 1");
        hibernateProps.put("hibernate.c3p0.autoCommitOnClose", true);
        hibernateProps.put("hibernate.c3p0.testConnectionOnCheckout", true);

        hibernateProps.put("org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
        return hibernateProps;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource());
        sessionFactory.setPackagesToScan("com.cafe.managerassignment.model");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setCachePreparedStatements(true);
        dataSource.setCachePrepStmts(true);
        dataSource.setCacheResultSetMetadata(true);
        return dataSource;
    }

    @Bean(name = "jdbcTemplateMySql")
    JdbcTemplate jdbcTemplateMySql() {
        JdbcTemplate template = new JdbcTemplate(mysqlDataSource());
        template.setFetchSize(fetchSize);
        return template;
    }

}
*/