package eu.athumi.dao.demoburgerlijkestand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("eu.athumi.dao")
public class DemoBurgerlijkeStandApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBurgerlijkeStandApplication.class, args);
    }

}
