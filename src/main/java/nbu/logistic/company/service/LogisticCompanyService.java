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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class LogisticCompanyService {

    LogisticCompanyRepository logisticCompanyRepository;
    CompanyMapper companyMapper;

    public void updateLogisticCompany(LogisticCompanyDto logisticCompanyDto) {

        LogisticCompany company = logisticCompanyRepository.findByName(logisticCompanyDto.getName());

        company.setName(logisticCompanyDto.getName());
        company.setCountry(logisticCompanyDto.getCountry());
        company.setCentralOfficeAddress(logisticCompanyDto.getCentralOfficeAddress());
        company.setCity(logisticCompanyDto.getCity());

        log.info("Company {} was updated", logisticCompanyDto.getName());
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

    public void deleteCompany(String name) {
        logisticCompanyRepository.deleteByName(name);

        log.info("Company {} was deleted", name);
    }

    public Optional<LogisticCompany> findById(Long id) {
        return logisticCompanyRepository.findById(id);
    }
}
