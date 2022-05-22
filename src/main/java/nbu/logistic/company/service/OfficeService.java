package nbu.logistic.company.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbu.logistic.company.api.dto.OfficeDto;
import nbu.logistic.company.api.exceptions.GeneralApiException;
import nbu.logistic.company.domain.LogisticCompany;
import nbu.logistic.company.domain.Office;
import nbu.logistic.company.mapper.OfficeMapper;
import nbu.logistic.company.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfficeService {

    OfficeRepository officeRepository;
    OfficeMapper officeMapper;
    LogisticCompanyService logisticCompanyService;

    public List<OfficeDto> getOffices() {
        return officeRepository.findAll()
                .stream()
                .map(officeMapper::toOfficeDto)
                .collect(Collectors.toList());
    }

    public void createOffice(OfficeDto officeDto) {
        Office office = officeMapper.fromDtoToOffice(officeDto);

        final Long logisticCompanyId = officeDto.getLogisticCompanyId();
        Optional<LogisticCompany> company = logisticCompanyService.findById(logisticCompanyId);
        if (company.isEmpty()) {
            throw new GeneralApiException(
                    String.format("Company with id: %s does not exists. Office cannot be created without company reference",
                            logisticCompanyId));
        }

        office.setLogisticCompany(company.get());

        officeRepository.save(office);
    }

    public void updateOffice(Long id, OfficeDto officeDto) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new GeneralApiException(String.format("Office with id: %s does not exist!", id)));

        office.setAddress(officeDto.getAddress());
        office.setCity(officeDto.getCity());
        office.setOfficeName(officeDto.getOfficeName());

        Optional<LogisticCompany> logisticCompany = logisticCompanyService.findById(officeDto.getLogisticCompanyId());
        logisticCompany.ifPresent(office::setLogisticCompany);
    }

    public void deleteOffice(Long id) {
        officeRepository.deleteById(id);
    }

    public Optional<Office> findById(Long id) {
        return officeRepository.findById(id);
    }
}
