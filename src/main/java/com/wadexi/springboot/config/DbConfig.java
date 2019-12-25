package com.wadexi.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.TransactionManagerImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.beans.PropertyVetoException;


@Configuration
@EnableTransactionManagement
@MapperScan(
        basePackages= "com.wadexi.springboot.web.mapper",
        annotationClass = Mapper.class
)
@PropertySource(
        value = {
            "classpath:prodb.properties",
            "classpath:devdb.properties"
        }
)
public class DbConfig /*implements TransactionManagementConfigurer*/{

    @Value("${db.driverClass}")
    private String driverClass;
    @Value("${db.jdbcUrl}")
    private String jdbcUrl;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;

    @Value("${pro.db.driverClass}")
    private String proDriverClass;
    @Value("${pro.db.jdbcUrl}")
    private String proJdbcUrl;
    @Value("${pro.db.user}")
    private String proUser;
    @Value("${pro.db.password}")
    private String proPassword;

    @Bean("dataSource")
    @Profile("dev")
//    @ConfigurationProperties
    public DataSource devDataSource() throws PropertyVetoException {
        DruidXADataSource druidDataSource = new DruidXADataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setBeanName("db");
        atomikosDataSourceBean.setXaDataSource(druidDataSource);

        return atomikosDataSourceBean;
    }



    @Bean(initMethod = "init" ,destroyMethod = "close")
    public UserTransactionManager annotationDrivenTransactionManager()  {

        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager(){
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        jtaTransactionManager.setTransactionManager(annotationDrivenTransactionManager());
        return jtaTransactionManager;
    }

//    @Bean("dataSource")
////    @Profile("pro")
////    @ConfigurationProperties
//    public DataSource proDataSource() throws PropertyVetoException {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(proDriverClass);
//        dataSource.setUrl(proJdbcUrl);
//        dataSource.setUsername(proUser);
//        dataSource.setPassword(proPassword);
//
//        return dataSource;
//    }

//    @Bean
//    @Transactional
//    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
//        JdbcTemplate jdbcTemplate1 = new JdbcTemplate(devDataSource());
//        jdbcTemplate1.execute();
//        jdbcTemplate1.getDataSource().getConnection()
//        return jdbcTemplate1;
//    }
//
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.wadexi.springboot.web.pojo");
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory object = sqlSessionFactoryBean.getObject();

        return sqlSessionFactoryBean.getObject();
    }





//    @Bean
//    public PlatformTransactionManager annotationDrivenTransactionManager() throws PropertyVetoException {
//        return new DataSourceTransactionManager(proDataSource());
//    }



}
