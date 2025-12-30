package eu.athumi.dao.demoburgerlijkestand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@ConfigurationPropertiesScan("eu.athumi.dao")
@EnableConfigurationProperties
public class DemoBurgerlijkeStandApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Brussels"));

        SpringApplication.run(DemoBurgerlijkeStandApplication.class, args);
    }

}
