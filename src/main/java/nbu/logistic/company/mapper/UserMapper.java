package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.ApiUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    UserDto fromApiUserToUserDto(ApiUser apiUser);
}
