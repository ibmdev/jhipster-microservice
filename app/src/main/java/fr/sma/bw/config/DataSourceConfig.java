package fr.sma.bw.config;

import com.zaxxer.hikari.HikariDataSource;

import fr.sma.bw.constants.EnumConstants;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "fr.sma.bw.repositories.mybatis", sqlSessionTemplateRef = "SessionTemplate")
public class DataSourceConfig {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;
    

    /* JDBC TEMPLATE */
    @Bean(name="JdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier(EnumConstants.DSNAME) final DataSource dataSource) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        // jdbcTemplate.setQueryTimeout(300000);
        return jdbcTemplate;
    }
    

    /* DATASOURCE */
    @Bean(name = EnumConstants.DSNAME)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(@Value("${spring.datasource.hikari.schema:"+
    EnumConstants.HIKARI_SCHEMA_UNDEFINED+"}") final String schema) throws IllegalArgumentException {
    
    	if((EnumConstants.HIKARI_SCHEMA_UNDEFINED.equalsIgnoreCase(schema) 
        		|| EnumConstants.HIKARI_SCHEMA_EMPTY.equalsIgnoreCase(schema)) 
        		&& EnumConstants.SPRING_PROFILE_PRODUCTION.equalsIgnoreCase(activeProfile)) {
           throw new IllegalArgumentException("Veuillez spécifier un schema dans la configuration spring.datasource.hikari");
        }
        final DataSource ds = DataSourceBuilder.create().build();
        if(!EnumConstants.HIKARI_SCHEMA_UNDEFINED.equalsIgnoreCase(schema) 
       	&& EnumConstants.SPRING_PROFILE_PRODUCTION.equalsIgnoreCase(activeProfile)) {
            ((HikariDataSource)ds).setSchema(schema);
        }
        return ds;
    }

    /*  SessionFactory */
    @Bean(name = "SessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier(EnumConstants.DSNAME) final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /*  DataSourceTransactionManager */
    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(EnumConstants.DSNAME) final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /* SqlSessionTemplate  */
    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate sessionTemplate(@Qualifier("SessionFactory") final SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
