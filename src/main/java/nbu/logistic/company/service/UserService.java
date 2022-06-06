package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.api.exceptions.GeneralApiException;
import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.enums.Roles;
import nbu.logistic.company.repository.RoleRepository;
import nbu.logistic.company.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiUser user = userRepository.findByUsername(username);

        if (user == null) {
            final String message = "User: {} not found in DB";
            log.error(message, username);

            throw new UsernameNotFoundException(message);
        }

        log.info("User: {} found in DB", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public ApiUser registerUser(UserDto userDto) {
        ApiUser apiUser = new ApiUser();
        apiUser.setPassword(userDto.getPassword());
        apiUser.setName(userDto.getName());
        apiUser.setUsername(userDto.getUsername());
        apiUser.setPassword(passwordEncoder.encode(apiUser.getPassword()));
        apiUser = userRepository.save(apiUser);

        addRoleToUser(apiUser.getUsername(), Roles.CLIENT.name());

        return apiUser;
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} to the DB", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String userName, String roleName) {

        log.info("Adding role: {} to user: {} ", roleName, userName);

        ApiUser apiUser = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        apiUser.getRoles().add(role);
        userRepository.save(apiUser);

        //no need of calling save method, as the whole class is marked as transactional
    }

    public void removeRoleForUser(String userName, String roleName) {

        log.info("Removing role: {} to user: {} ", roleName, userName);

        ApiUser apiUser = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        apiUser.getRoles().remove(role);
        userRepository.save(apiUser);

        //no need of calling save method, as the whole class is marked as transactional
    }
    public ApiUser getUser(String userName) {
        log.info("Fetching user: {} ", userName);

        return userRepository.findByUsername(userName);
    }

    public List<ApiUser> getUsers() {
        log.info("Fetching all users");

        return userRepository.findAll();
    }

    public void updateUser(UserDto userDto) {
        ApiUser apiUser = userRepository.findByUsername(userDto.getUsername());

        apiUser.setUsername(userDto.getUsername());
        apiUser.setName(userDto.getName());
        apiUser.setPassword(userDto.getPassword());

        log.info("User {} is updated", userDto.getUsername());

    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);

        log.info("User {} is deleted", username);
    }
}
