package nbu.logistic.company.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.service.CrudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANIES_GET;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_CREATE;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_DELETE;
import static nbu.logistic.company.api.constants.Endpoints.LOGISTIC_COMPANY_UPDATE;
import static nbu.logistic.company.api.constants.Endpoints.ROOT;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CrudController {

    CrudService crudService;

    @PostMapping(LOGISTIC_COMPANY_UPDATE)
    public void updateLogisticCompany(@PathVariable Long id, LogisticCompanyDto logisticCompanyDto) {
        crudService.updateLogisticCompany(id, logisticCompanyDto);
    }

    @GetMapping(LOGISTIC_COMPANIES_GET)
    public List<LogisticCompanyDto> getLogisticCompanies() {
        return crudService.getCompanies();
    }

    @PostMapping(LOGISTIC_COMPANY_CREATE)
    public void createLogisticCompany(@RequestBody LogisticCompanyDto logisticCompanyDto) {
        crudService.createLogisticCompany(logisticCompanyDto);
    }

    @DeleteMapping(LOGISTIC_COMPANY_DELETE)
    public void deleteLogisticCompany(@PathVariable Long id) {
        crudService.deleteLogisticCompary(id);
    }



}
