package com.designedbyhenryp.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {

		SpringApplication.run(UrlShortenerApplication.class, args);
	}
}
