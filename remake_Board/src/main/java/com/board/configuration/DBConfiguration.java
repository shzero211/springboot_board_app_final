package com.board.configuration;


import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {
	//스프링컨테이너 
	@Autowired
	private ApplicationContext applicationContext;
	
	//application.properties에서 저 앞문장있는 설정들은 가져옴
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	//커넥션풀을 지원하기위한 인터페이스
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	//마이바티스와 스프링의 연동모듈
	@Bean
	public SqlSessionFactory sqlSessionFactory()throws Exception{
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		factoryBean.setTypeAliasesPackage("com.board.domain");
		factoryBean.setConfiguration(mybatisConfg());
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfg(){
		return new org.apache.ibatis.session.Configuration();
		
	}
}
