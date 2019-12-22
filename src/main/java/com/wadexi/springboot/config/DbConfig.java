package com.wadexi.springboot.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
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
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        return comboPooledDataSource;
    }

    @Bean("dataSource")
    @Profile("pro")
//    @ConfigurationProperties
    public DataSource proDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(proDriverClass);
        dataSource.setJdbcUrl(proJdbcUrl);
        dataSource.setUser(proUser);
        dataSource.setPassword(proPassword);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.wadexi.springboot.web.bean");
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
