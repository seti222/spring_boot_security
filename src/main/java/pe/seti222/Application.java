package pe.seti222;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.seti222.domain.Role;
import pe.seti222.domain.User;
import pe.seti222.repository.UserRepository;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepo;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
        return application.sources(Application.class);
    }

    @Bean
    public User initAdminUser(){
    	List<User> list = userRepo.findAll();
    	User user = null ;
    	if(list.size() == 0) {
    		user = new User();
    		user.setUserId("demo");
    		user.setRole(Role.ADMIN);
    		user.setPasswordHash(new BCryptPasswordEncoder().encode("demo"));
    		userRepo.save(user);
    	}
    	return user;
    }
}