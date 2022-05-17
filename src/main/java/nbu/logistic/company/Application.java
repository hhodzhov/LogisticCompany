package nbu.logistic.company;

import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//executes after application starts
//	@Bean
//	CommandLineRunner run (UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ADMIN"));
//			userService.saveRole(new Role(null, "AGENT"));
//			userService.saveRole(new Role(null, "COURIER"));
//			userService.saveRole(new Role(null, "CLIENT"));
//
//			userService.saveUser(new ApiUser(null, "John Travolta", "john", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Will Smith", "will", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("john", "ROLE_USER");
//			userService.addRoleToUser("john", "ROLE_MANAGER");
//			userService.addRoleToUser("will", "ROLE_MANAGER");
//			userService.addRoleToUser("jim", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_USER");
//		};
//	}
}
