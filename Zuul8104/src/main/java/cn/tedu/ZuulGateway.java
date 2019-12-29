package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulGateway {
	public static void main(String[] args) {
		SpringApplication.run(ZuulGateway.class, args);
	} 
}
