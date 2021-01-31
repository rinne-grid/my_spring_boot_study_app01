package jp.co.rngd.myspringboot;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

public class MySpringBootApplicationTestConfiguration {
	@Bean
	public DataSource dataSource() {
		return new TransactionAwareDataSourceProxy(
				 DataSourceBuilder
				 .create()
				 .username("springuser")
				 .password("springuser")
				 .url("jdbc:mysql://192.168.99.101:3306/my_spring_boot")
				 .driverClassName("com.mysql.cj.jdbc.Driver")
				 .build()
				);
	}
}
