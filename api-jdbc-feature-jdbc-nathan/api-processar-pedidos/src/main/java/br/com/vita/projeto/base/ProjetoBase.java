package br.com.vita.projeto.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.vita.projeto.base.model.entities"} )
@EnableJpaRepositories(basePackages = {"br.com.vita.projeto.base.repository"})
@EnableMongoRepositories(basePackages = {"br.com.vita.projeto.base.repository"})
@EnableScheduling
public class ProjetoBase {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBase.class, args);
	}
}
