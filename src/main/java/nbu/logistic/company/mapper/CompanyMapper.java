package nbu.logistic.company.mapper;

import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.domain.LogisticCompany;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CompanyMapper {

    @Mapping(target = "id", source = "id")
    LogisticCompanyDto toLogisticCompanyDto(LogisticCompany logisticCompany);

    LogisticCompany toLogisticCompany(LogisticCompanyDto logisticCompanyDto);
}
