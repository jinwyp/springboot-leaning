package com.example.demo;

import com.example.demo.customReturnValueHandler.ResponseBodyWrapFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		System.out.println(" ============================== Spring Boot Starting :  ============================== ");

	}


	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println(" ============================== Let's inspect the beans provided by Spring Boot: ============================== ");

//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println("Beans name: " + beanName );
//			}

			System.out.println(" ============================== End ============================== ");
		};
	}


//	@Bean
//	public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
//		return new ResponseBodyWrapFactoryBean();
//	}

}
