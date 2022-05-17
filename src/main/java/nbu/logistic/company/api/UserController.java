package nbu.logistic.company.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import nbu.logistic.company.api.dto.CommonApiResponse;
import nbu.logistic.company.api.dto.RoleToUserDto;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nbu.logistic.company.api.constants.Endpoints.ADD_ROLE_TO_USER;
import static nbu.logistic.company.api.constants.Endpoints.CLIENT_REGISTER;
import static nbu.logistic.company.api.constants.Endpoints.ROLE_SAVE;
import static nbu.logistic.company.api.constants.Endpoints.ROOT;
import static nbu.logistic.company.api.constants.Endpoints.TOKEN_REFRESH;
import static nbu.logistic.company.api.constants.Endpoints.USERS;
import static nbu.logistic.company.api.constants.Endpoints.USER_SAVE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping(USERS)
    public ResponseEntity<List<ApiUser>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping(USER_SAVE)
    public ResponseEntity<ApiUser> saveUser(@RequestBody ApiUser apiUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(apiUser));
    }

    @PostMapping(CLIENT_REGISTER)
    public ResponseEntity<CommonApiResponse> registerClient(@RequestBody UserDto userDto) {

        try {
            userService.registerUser(userDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder.fromCurrentContextPath().path(ROOT + CLIENT_REGISTER).toUriString());

            return ResponseEntity
                    .created(uri)
                    .body(new CommonApiResponse(true, "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonApiResponse(false, e.getMessage()));
        }

    }

    @PostMapping(ROLE_SAVE)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping(ADD_ROLE_TO_USER)
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto roleToUserDto) {
        userService.addRoleToUser(roleToUserDto.getUserName(), roleToUserDto.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(TOKEN_REFRESH)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //get the actual token
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                ApiUser apiUser = userService.getUser(username);
                String accessToken = JWT.create()
                        //something unique for the user
                        .withSubject(apiUser.getUsername())
                        //expiration date of the refreshToken -> 10 minutes currently
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        //put the roles of this user
                        .withClaim("roles",
                                apiUser.getRoles()
                                        .stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                log.error("Error logging in: {}", e.getMessage());
                response.setHeader("Error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
