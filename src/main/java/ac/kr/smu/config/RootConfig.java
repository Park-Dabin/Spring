package ac.kr.smu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("ac.kr.smu.mapper")
@EnableTransactionManagement
@ComponentScan("ac.kr.smu.service")
public class RootConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ComboPooledDataSource comboPooledDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
            dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/spring?allowMultiQueries=true");
            dataSource.setUser("박다빈");
            dataSource.setPassword("dabin0616");
            dataSource.setCheckoutTimeout(3000);
        }catch (Exception e){e.printStackTrace();}
        return dataSource;
    }
    /*
     MyBatis 설정을 위한 클래스
    */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSources){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try {
            sqlSessionFactoryBean.setDataSource(dataSources);
            sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
            sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
        }catch (IOException e){ e.printStackTrace();}

        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactoryBean){
        return new SqlSessionTemplate(sqlSessionFactoryBean);
    }
    /*
    	트랜잭션을 위한 클래스
    */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
