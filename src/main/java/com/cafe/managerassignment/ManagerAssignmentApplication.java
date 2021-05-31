package com.cafe.managerassignment;

import com.cafe.managerassignment.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class ManagerAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerAssignmentApplication.class, args);
    }

}
