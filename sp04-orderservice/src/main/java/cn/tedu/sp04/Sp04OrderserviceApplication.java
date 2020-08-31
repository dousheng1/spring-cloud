package cn.tedu.sp04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication*/
@EnableFeignClients
@SpringCloudApplication
public class Sp04OrderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sp04OrderserviceApplication.class, args);
    }

}
