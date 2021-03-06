package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.domain.Office;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OfficeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "logisticCompanyId", expression = "java( office.getLogisticCompany() != null ? office.getLogisticCompany().getId() : null )")
    OfficeDto toOfficeDto(Office office);

    Office fromDtoToOffice(OfficeDto officeDto);
}
