package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.domain.Office;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OfficeMapper {

    @Mapping(target = "logisticCompanyId", expression = "java( office.getLogisticCompany() != null ? office.getLogisticCompany().getId() : null )")
    OfficeDto toOfficeDto(Office office);

    Office fromDtoToOffice(OfficeDto officeDto);
}
