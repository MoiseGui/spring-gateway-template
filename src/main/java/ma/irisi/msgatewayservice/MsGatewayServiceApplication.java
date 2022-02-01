package ma.irisi.msgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGatewayServiceApplication.class, args);
    }

}
