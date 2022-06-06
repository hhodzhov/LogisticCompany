package nbu.logistic.company.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.CommonApiResponse;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.api.dto.RoleToUserDto;
import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.enums.ShipmentStatus;
import nbu.logistic.company.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static nbu.logistic.company.api.constants.Endpoints.*;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrudController {

    CrudService crudService;

    // Logistic Company Crud Endpoints
    @PostMapping(LOGISTIC_COMPANY_UPDATE)
    public void updateLogisticCompany(@RequestBody LogisticCompanyDto logisticCompanyDto) {
        crudService.updateLogisticCompany(logisticCompanyDto);
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
    public void deleteLogisticCompany(@PathVariable String name) {
        crudService.deleteLogisticCompary(name);
    }


    // User Crud Endpoints
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
    public void userUpdate(@RequestBody UserDto userDto) {
        crudService.updateClient(userDto);
    }

    @DeleteMapping(USER_DELETE)
    public void deleteUser(@PathVariable String username) {
        crudService.deleteUser(username);
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

    @PostMapping(REMOVE_ROLE_FOR_USER)
    public ResponseEntity<?> removeRoleFoUser(@RequestBody RoleToUserDto roleToRemove) {
        crudService.removeRoleForUser(roleToRemove.getUserName(), roleToRemove.getRoleName());
        return ResponseEntity.ok().build();
    }

    // Office Crud Endpoints
    @GetMapping(OFFICE_GET)
    public ResponseEntity<List<OfficeDto>> getOffices() {
        return ResponseEntity.ok(crudService.getOffices());
    }

    @PostMapping(OFFICES_CREATE)
    public void createOffice(@RequestBody OfficeDto officeDto) {
        crudService.createOffice(officeDto);
    }

    @PostMapping(OFFICE_UPDATE)
    public void updateOffice(@RequestBody OfficeDto officeDto) {
        crudService.updateOffice(officeDto);
    }

    @DeleteMapping(OFFICE_DELETE)
    public void deleteOffice(@PathVariable String name) {
        crudService.deleteOffice(name);
    }

    // Shipment Crud Endpoints
    @GetMapping(SHIPMENT_GET)
    public ResponseEntity<List<ShipmentDto>> getAllShipments(
            @RequestParam(required = false) String agent,
            @RequestParam(required = false) ShipmentStatus status,
            @RequestParam(required = false) String sentFrom,
            @RequestParam(required = false) String receivedFrom
    ) {
        return ResponseEntity.ok(crudService.getAllShipments(agent, status, sentFrom, receivedFrom));
    }

    @PostMapping(SHIPMENT_UPDATE)
    public void updateShipment(@PathVariable Long id, @RequestBody ShipmentDto shipmentDto) {
        crudService.updateShipment(id, shipmentDto);
    }

    @PostMapping(SHIPMENT_CREATE)
    public void createShipment(@RequestBody ShipmentDto shipmentDto) {
        crudService.createShipment(shipmentDto);
    }

    @DeleteMapping(SHIPMENT_DELETE)
    public void deleteShipment(@PathVariable Long id) {
        crudService.deleteShipment(id);
    }

    @GetMapping(GET_PROFIT)
    public ResponseEntity<Double> getProfit(
            @RequestParam Instant from,
            @RequestParam Instant to
    ) {
        return ResponseEntity.ok(crudService.getProfitFromTo(
                LocalDateTime.ofInstant(from, ZoneId.systemDefault()),
                LocalDateTime.ofInstant(to, ZoneId.systemDefault())));
    }

}
