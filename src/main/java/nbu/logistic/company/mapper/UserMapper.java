package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.UserDto;
import nbu.logistic.company.domain.ApiUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

//    @Mapping(target = "name", expression = "java(userDto.getName())")
//    @Mapping(target = "username", expression = "java(userDto.getUsername())")
//    @Mapping(target = "password", expression = "java(userDto.getPassword())")
//    ApiUser fromUserDtoToApiUser(UserDto userDto);

//    @Mapping(target = "name", expression = "java(userDto.getName())")
//    @Mapping(target = "username", expression = "java(userDto.getUsername())")
//    @Mapping(target = "password", expression = "java(userDto.getPassword())")
    UserDto fromApiUserToUserDto(ApiUser apiUser);
}
