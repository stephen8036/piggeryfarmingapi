package com.veruela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.veruela"}, exclude = { SecurityAutoConfiguration.class })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages ="com.veruela.repositories", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class )
@EntityScan("com.veruela.model")
@Configuration
public class VeruelaDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeruelaDatabaseApplication.class, args);
	}

}
