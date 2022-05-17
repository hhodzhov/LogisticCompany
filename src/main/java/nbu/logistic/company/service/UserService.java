package nbu.logistic.company.service;

import nbu.logistic.company.domain.ApiUser;
import nbu.logistic.company.domain.Role;

import java.util.List;

public interface UserService {

    ApiUser saveUser(ApiUser apiUser);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    ApiUser getUser(String userName);

    List<ApiUser> getUsers();
}
