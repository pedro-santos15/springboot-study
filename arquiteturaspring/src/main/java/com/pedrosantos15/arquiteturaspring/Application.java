package com.pedrosantos15.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

        //para mexer diretamente no builder da aplicação, deve-se ser alterado antes da aplicação inicializar
        builder.bannerMode(Banner.Mode.OFF);
        builder.profiles("producao");
        builder.run(args);

        //Para manipular os dados do contexto da aplicação, a mesma ja deve ter sido iniciada
        ConfigurableApplicationContext applicationContext = builder.context();

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String applicationName = environment.getProperty("spring.application.name");

        System.out.println("Nome da aplicação: " + applicationName);

	}

}
