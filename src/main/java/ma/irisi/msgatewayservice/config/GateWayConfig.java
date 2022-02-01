package ma.irisi.msgatewayservice.config;

import ma.irisi.msgatewayservice.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth-service/**").filters(f -> f.filter(filter)).uri("lb://auth-service"))
                .route("gestion-paie-service", r -> r.path("/gestion-paie/**").filters(f -> f.filter(filter)).uri("lb://gestion-paie-service"))
                .route("gestion-virement", r -> r.path("/gestion-virement/**").filters(f -> f.filter(filter)).uri("lb://gestion-virement"))
                .route("ms-mailler", r -> r.path("/ms-mailler/**").filters(f -> f.filter(filter)).uri("lb://ms-mailler"))
                .build();
    }
}
