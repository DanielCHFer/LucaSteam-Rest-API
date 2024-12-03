package com.ejemplos.spring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestLucaSteamApplication {

    private static final Logger logger = LoggerFactory.getLogger(RestLucaSteamApplication.class);

	
	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		SpringApplication.run(RestLucaSteamApplication.class, args);
	}

}
