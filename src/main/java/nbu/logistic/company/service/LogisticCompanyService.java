package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import nbu.logistic.company.api.dto.LogisticCompanyDto;
import nbu.logistic.company.api.exceptions.GeneralApiException;
import nbu.logistic.company.domain.LogisticCompany;
import nbu.logistic.company.mapper.CompanyMapper;
import nbu.logistic.company.repository.LogisticCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class LogisticCompanyService {

    LogisticCompanyRepository logisticCompanyRepository;
    CompanyMapper companyMapper;

    public void updateLogisticCompany(Long id, LogisticCompanyDto logisticCompanyDto) {

        LogisticCompany company = logisticCompanyRepository.findById(id)
                .orElseThrow(() -> new GeneralApiException(String.format("Logistic company with id: %s not found", id)));

        company.setName(logisticCompanyDto.getName());
        company.setCountry(logisticCompanyDto.getCountry());
        company.setCentralOfficeAddress(logisticCompanyDto.getCentralOfficeAddress());
        company.setCity(logisticCompanyDto.getCity());

        log.info("Company with id: {} was updated", id);
    }

    public List<LogisticCompanyDto> getCompanies() {
        return logisticCompanyRepository.findAll()
                .stream()
                .map(companyMapper::toLogisticCompanyDto)
                .collect(Collectors.toList());
    }

    public void createCompany(LogisticCompanyDto logisticCompanyDto) {

        LogisticCompany company = companyMapper.toLogisticCompany(logisticCompanyDto);

        logisticCompanyRepository.save(company);

        log.info("New company was created successfully");
    }

    public void deleteCompany(Long id) {
        logisticCompanyRepository.deleteById(id);

        log.info("Company with id: {} was deleted", id);
    }
}
