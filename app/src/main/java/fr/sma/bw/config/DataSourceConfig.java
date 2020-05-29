package fr.sma.bw.config;

import com.zaxxer.hikari.HikariDataSource;
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
    private transient String activeProfile;
    
    private transient static final String DSName = "DataSource";
    // Literals
    private transient static final String Hikari_Schema_Undefined = "undefined";
    private transient static final String Hikari_Schema_Empty = "";
    private transient static final String Spring_Profile_Production = "prod";


    /* JDBC TEMPLATE */
    @Bean(name="JdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier(DSName) DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.setQueryTimeout(300000);
        return jdbcTemplate;
    }

    /* DATASOURCE */
    @Bean(name = DSName)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource(@Value("${spring.datasource.hikari.schema:"+Hikari_Schema_Undefined+"}") String schema) throws Exception {
        if((Hikari_Schema_Undefined.equalsIgnoreCase(schema) || Hikari_Schema_Empty.equalsIgnoreCase(schema)) && Spring_Profile_Production.equalsIgnoreCase(activeProfile)) {
           throw new Exception("Veuillez spécifier un schema dans la configuration spring.datasource.hikari");
        }
        DataSource ds = DataSourceBuilder.create().build();
        if(!Hikari_Schema_Undefined.equalsIgnoreCase(schema) && Spring_Profile_Production.equalsIgnoreCase(activeProfile)) {
            ((HikariDataSource)ds).setSchema(schema);
        }
        return ds;
    }

    /*  SessionFactory */
    @Bean(name = "SessionFactory")
    public SqlSessionFactory SessionFactory(@Qualifier(DSName) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /*  DataSourceTransactionManager */
    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager TransactionManager(@Qualifier(DSName) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /* SqlSessionTemplate  */
    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SessionTemplate(@Qualifier("SessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
