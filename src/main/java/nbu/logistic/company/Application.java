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
	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
//			userService.saveRole(new Role(null, "ADMIN"));
//			userService.saveRole(new Role(null, "AGENT"));
//			userService.saveRole(new Role(null, "COURIER"));
//			userService.saveRole(new Role(null, "CLIENT"));
//
//			userService.saveUser(new ApiUser(null, "Ivan Ivanov", "vankata1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Petar Petrov", "pesho1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Alexander Stoyanov", "sashenkata1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Petar Tudzharov", "djaro1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Vladimir Popov", "popov1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Martin Hristov", "hristov1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Ivaylo Zahariev", "ivak1", "1234", new ArrayList<>()));
//			userService.saveUser(new ApiUser(null, "Vladimir Chervenski", "vlado1", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("vankata1", "CLIENT");
//			userService.addRoleToUser("pesho1", "CLIENT");
//			userService.addRoleToUser("vlado1", "CLIENT");
//			userService.addRoleToUser("sashenkata1", "COURIER");
//			userService.addRoleToUser("ivak1", "COURIER");
//			userService.addRoleToUser("popov1", "AGENT");
//			userService.addRoleToUser("hristov1", "AGENT");
//			userService.addRoleToUser("djaro1", "ADMIN");
		};
	}
}
