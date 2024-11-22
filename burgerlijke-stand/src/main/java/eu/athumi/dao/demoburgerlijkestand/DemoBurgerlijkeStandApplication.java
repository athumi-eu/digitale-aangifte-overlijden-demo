package eu.athumi.dao.demoburgerlijkestand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("eu.athumi.dao")
@EnableConfigurationProperties
public class DemoBurgerlijkeStandApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBurgerlijkeStandApplication.class, args);
    }

}
