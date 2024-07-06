package service.backend_spring3;

import service.backend_spring3.domain.User;
import service.backend_spring3.repository.*;
import service.backend_spring3.service.LoginService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class SpringConfig {


//    private final UserRepository userRepository;
//    @Autowired
//    public SpringConfig(UserRepository userRepository, EntityManager em){
//        this.userRepository = userRepository;
//    }

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public LoginService loginService() {

        return new LoginService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
          return new JdbcTemplateUserRepository(dataSource);

    }
}
