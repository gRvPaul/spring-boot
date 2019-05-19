package com.cma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * @author UNGERW
 */
//@ComponentScan
//@EnableAutoConfiguration
//@SpringBootApplication


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories(basePackages = "com.cma.dao")
@PropertySource("classpath:/application.properties")
public class Application {

    public static void main(String[] args) throws Exception {
	SpringApplication app = new SpringApplication(Application.class);

	app.run(args);

        //ApplicationContext ctx = SpringApplication.run(Application.class, args);

        // Print out the loaded bean definitions
        //List<String> beanNames = Arrays.asList(ctx.getBeanDefinitionNames());
        //beanNames.forEach((name)->System.out.println(" - " + name));

    }

}
