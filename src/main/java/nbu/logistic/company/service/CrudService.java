package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.Role;
import nbu.logistic.company.mapper.UserMapper;
import org.springframework.stereotype.Service;

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

    public void updateLogisticCompany(Long id, LogisticCompanyDto logisticCompanyDto) {
        logisticCompanyService.updateLogisticCompany(id, logisticCompanyDto);
    }

    public List<LogisticCompanyDto> getCompanies() {
        return logisticCompanyService.getCompanies();
    }

    public void createLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        logisticCompanyService.createCompany(logisticCompanyDto);
    }

    public void deleteLogisticCompary(Long id) {
        logisticCompanyService.deleteCompany(id);
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

    public void updateClient(Long id, UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
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

    public void updateOffice(Long id, OfficeDto officeDto) {
        officeService.updateOffice(id, officeDto);
    }

    public void deleteOffice(Long id) {
        officeService.deleteOffice(id);
    }
}
