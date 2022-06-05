package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.api.dto.ShipmentDto;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.enums.ShipmentStatus;
import nbu.logistic.company.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrudService {

    LogisticCompanyService logisticCompanyService;
    UserService userService;
    UserMapper userMapper;
    OfficeService officeService;
    ShipmentService shipmentService;

    public void updateLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        logisticCompanyService.updateLogisticCompany(logisticCompanyDto);
    }

    public List<LogisticCompanyDto> getCompanies() {
        return logisticCompanyService.getCompanies();
    }

    public void createLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        logisticCompanyService.createCompany(logisticCompanyDto);
    }

    public void deleteLogisticCompary(String name) {
        logisticCompanyService.deleteCompany(name);
    }

    public void registerUser(UserDto userDto) {
        userService.registerUser(userDto);
    }

    public List<UserDto> getUsers() {
        return userService.getUsers()
                .stream()
                .map(userMapper::fromApiUserToUserDto)
                .collect(Collectors.toList());
    }

    public void updateClient(UserDto userDto) {
        userService.updateUser(userDto);
    }

    public void deleteUser(String username) {
        userService.deleteUser(username);
    }

    public Role saveRole(Role role) {
        return userService.saveRole(role);
    }

    public void addRoleToUser(String userName, String roleName) {
        userService.addRoleToUser(userName, roleName);
    }

    public List<OfficeDto> getOffices() {
        return officeService.getOffices();
    }

    public void createOffice(OfficeDto officeDto) {
        officeService.createOffice(officeDto);
    }

    public void updateOffice(OfficeDto officeDto) {
        officeService.updateOffice(officeDto);
    }

    public void deleteOffice(String name) {
        officeService.deleteOffice(name);
    }

    public List<ShipmentDto> getAllShipments(
            String agent,
            ShipmentStatus status,
            String sentFrom,
            String receivedFrom
    ) {
        return shipmentService.getAllShipments()
                .stream()
                .filter(s -> status == null || s.getShipmentStatus() == status)
                .filter(s -> sentFrom == null || s.getSenderName().equals(sentFrom))
                .filter(s -> receivedFrom == null || s.getRecipientName().equals(receivedFrom))
                .filter(s -> agent == null || s.getAgent().equals(agent))
                .collect(Collectors.toList());
    }

    public double getProfitFromTo(LocalDateTime from, LocalDateTime to) {
        return shipmentService.getProfitFromTo(from, to);
    }

    public void updateShipment(Long id, ShipmentDto shipmentDto) {
        shipmentService.updateShipment(id, shipmentDto);
    }

    public void deleteShipment(Long id) {
        shipmentService.delete(id);
    }

    public void createShipment(ShipmentDto shipmentDto) {
        shipmentService.createShipment(shipmentDto);
    }
}
