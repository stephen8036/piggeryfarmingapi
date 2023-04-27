package com.piggery;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@SpringBootApplication(scanBasePackages = {"com.piggery"}, exclude = { SecurityAutoConfiguration.class })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages ="com.piggery.repo", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class )
@EntityScan("com.piggery.models")
@Configuration
public class ApiPmsApplication extends SpringBootServletInitializer{
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiPmsApplication.class, args);
	}

}
