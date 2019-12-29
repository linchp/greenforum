package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StarterEureka {
	public static void main(String[] args) {
		SpringApplication.run(StarterEureka.class, args); 
	}
}
