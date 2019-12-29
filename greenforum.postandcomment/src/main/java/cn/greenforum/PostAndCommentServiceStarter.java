package cn.greenforum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("cn.greenforum.postandcomment.mapper")
@EnableEurekaClient
public class PostAndCommentServiceStarter {
	public static void main(String[] args) {
		SpringApplication.run(PostAndCommentServiceStarter.class, args);
	} 
}
