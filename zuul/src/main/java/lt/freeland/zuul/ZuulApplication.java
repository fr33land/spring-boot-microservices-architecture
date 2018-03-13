package lt.freeland.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableZuulProxy
@EnableOAuth2Sso
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("lt.freeland")
@EnableJpaRepositories("lt.freeland.repository")
@EntityScan("lt.freeland.beans")   
public class ZuulApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
    
}
