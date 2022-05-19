package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrudService {

    LogisticCompanyService logisticCompanyService;

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
}
