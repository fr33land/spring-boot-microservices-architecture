package lt.freeland.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(value = "lt.freeland.users.repository", repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@EntityScan("lt.freeland.common.domain")  
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }
}
