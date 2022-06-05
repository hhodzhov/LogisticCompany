package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.ApiUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
    @Mapping(target = "roles", expression = "java( apiUser.getRoles() != null ? apiUser.getRoles() : null )")
    UserDto fromApiUserToUserDto(ApiUser apiUser);
}
