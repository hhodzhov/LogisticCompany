package nbu.logistic.company.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.CommonApiResponse;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.api.dto.RoleToUserDto;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nbu.logistic.company.api.constants.Endpoints.ADD_ROLE_TO_USER;
import static nbu.logistic.company.api.constants.Endpoints.CLIENT_REGISTER;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANIES_GET;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_CREATE;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_DELETE;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_UPDATE;
import static nbu.logistic.company.api.constants.Endpoints.ROLE_CREATE;
import static nbu.logistic.company.api.constants.Endpoints.ROOT;
import static nbu.logistic.company.api.constants.Endpoints.USERS_GET;
import static nbu.logistic.company.api.constants.Endpoints.USER_CREATE;
import static nbu.logistic.company.api.constants.Endpoints.USER_DELETE;
import static nbu.logistic.company.api.constants.Endpoints.USER_UPDATE;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrudController {

    CrudService crudService;

    @PostMapping(LOGISTIC_COMPANY_UPDATE)
    public void updateLogisticCompany(@PathVariable Long id, LogisticCompanyDto logisticCompanyDto) {
        crudService.updateLogisticCompany(id, logisticCompanyDto);
    }

    @GetMapping(LOGISTIC_COMPANIES_GET)
    public List<LogisticCompanyDto> getLogisticCompanies() {
        return crudService.getCompanies();
    }

    @PostMapping(LOGISTIC_COMPANY_CREATE)
    public void createLogisticCompany(@RequestBody LogisticCompanyDto logisticCompanyDto) {
        crudService.createLogisticCompany(logisticCompanyDto);
    }

    @DeleteMapping(LOGISTIC_COMPANY_DELETE)
    public void deleteLogisticCompany(@PathVariable Long id) {
        crudService.deleteLogisticCompary(id);
    }


    @PostMapping(USER_CREATE)
    public ResponseEntity<CommonApiResponse> registerClient(@RequestBody UserDto userDto) {

        try {
            crudService.registerUser(userDto);

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

    @GetMapping(USERS_GET)
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(crudService.getUsers());
    }

    @PostMapping(USER_UPDATE)
    public void userUpdate(@PathVariable Long id, UserDto userDto) {
        crudService.updateClient(id, userDto);
    }

    @DeleteMapping(USER_DELETE)
    public void deleteUser(@PathVariable Long id) {
        crudService.deleteUser(id);
    }

    @PostMapping(ROLE_CREATE)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(ROLE_CREATE).toUriString());
        return ResponseEntity.created(uri).body(crudService.saveRole(role));
    }

    @PostMapping(ADD_ROLE_TO_USER)
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto roleToUserDto) {
        crudService.addRoleToUser(roleToUserDto.getUserName(), roleToUserDto.getRoleName());
        return ResponseEntity.ok().build();
    }



}
