package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"core"})
//@EnableJpaRepositories
//@EnableJpaAuditing
@EntityScan("core.entity")
public class SpringExperimentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExperimentalApplication.class, args);
    }

}
