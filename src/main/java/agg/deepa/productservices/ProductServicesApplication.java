package agg.deepa.productservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProductServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServicesApplication.class, args);
    }

}
